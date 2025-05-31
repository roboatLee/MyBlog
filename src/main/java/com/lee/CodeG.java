package com.lee;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author KitenLee
 * * @date 2025/5/31
 */
public class CodeG {
    public static void main(String[] args) {
        //这个url就是你的数据库的路由，详细请看你的url，username一般为root, password是你数据库的密码
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/myblog?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false",
                        "root",
                        "Lcx0618.")
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？"))
                        .outputDir("src\\main\\java")//这个里面的路径就是你的包的路径，由于我想让他们生成在一个文件夹下，默认生成到D盘让我很不舒服
                )
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？"))
                        .parent("com.lee")//这个按照你的包的名字进行编写
                )
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .entityBuilder()
                        .enableLombok()
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT)
                        )
                        .build())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
