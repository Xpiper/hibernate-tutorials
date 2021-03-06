package org.hibernate.domainmodel.namingStrategies;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.*;

import org.apache.commons.lang3.StringUtils;


/**
 * 这是一个名为Acme Corp的虚拟公司的物理命名策略示例，其命名标准为：
 * <p>
 * 喜欢用下划线定界的单词而不是驼峰式的单词
 * <p>
 * 用标准缩写替换某些单词
 */
public class AcmeCorpPhysicalNamingStrategy implements PhysicalNamingStrategy {

    private static final Map<String, String> ABBREVIATIONS = buildAbbreviationMap();


    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        // Acme naming standards do not apply to catalog names
        return name;
    }

    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        // Acme naming standards do not apply to schema names
        return name;
    }

    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        final List<String> parts = splitAndReplace(name.getText());
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                join(parts),
                name.isQuoted()
        );
    }

    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        final LinkedList<String> parts = splitAndReplace( name.getText() );
        // Acme Corp says all sequences should end with _seq
        if ( !"seq".equalsIgnoreCase( parts.getLast() ) ) {
            parts.add( "seq" );
        }
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                join( parts ),
                name.isQuoted()
        );
    }

    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        final List<String> parts = splitAndReplace( name.getText() );
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                join( parts ),
                name.isQuoted()
        );
    }


    private static Map<String, String> buildAbbreviationMap() {
        TreeMap<String, String> abbreviationMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        abbreviationMap.put("account", "acct");
        abbreviationMap.put("number", "num");
        return abbreviationMap;
    }


    private LinkedList<String> splitAndReplace(String name) {
        LinkedList<String> result = new LinkedList<String>();
        for (String part : StringUtils.splitByCharacterTypeCamelCase(name)) {
            if (part == null || part.trim().isEmpty()) {
                // skip null and space
                continue;
            }
            part = applyAbbreviationReplacement(part);
            result.add(part.toLowerCase(Locale.ROOT));
        }
        return result;
    }


    private String applyAbbreviationReplacement(String word) {
        if (ABBREVIATIONS.containsKey(word)) {
            return ABBREVIATIONS.get(word);
        }

        return word;
    }


    private String join(List<String> parts) {
        boolean firstPass = true;
        String separator = "";
        StringBuilder joined = new StringBuilder();
        for (String part : parts) {
            joined.append(separator).append(part);
            if (firstPass) {
                firstPass = false;
                separator = "_";
            }
        }
        return joined.toString();
    }


}
