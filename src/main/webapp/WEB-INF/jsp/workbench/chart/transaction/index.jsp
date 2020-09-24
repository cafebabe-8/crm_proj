<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/jsp/inc/commons.jsp"%>
    <script>

        jQuery(function ($) {
            // 统计处在不同阶段的交易数量 tran

            // 基于准备好的dom，初始化echarts实例
            let myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            optionForFunnel = {
                title: {
                    text: '漏斗图',
                    subtext: '纯属虚构'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c}"
                },
                toolbox: {
                    feature: {
                        dataView: {readOnly: false},
                        restore: {},
                        saveAsImage: {}
                    }
                },
                legend: {
                    data: ['展现', '点击', '访问', '咨询', '订单']
                },

                series: [
                    {
                        name: '漏斗图',
                        type: 'funnel',
                        left: '10%',
                        top: 60,
                        //x2: 80,
                        bottom: 60,
                        width: '85%',
                        // height: {totalHeight} - y - y2,
                        min: 0,
                        max: 100,
                        minSize: '0%',
                        maxSize: '100%',
                        sort: 'descending',
                        gap: 2,
                        label: {
                            show: true,
                            position: 'inside'
                        },
                        labelLine: {
                            length: 10,
                            lineStyle: {
                                width: 1,
                                type: 'solid'
                            }
                        },
                        itemStyle: {
                            borderColor: '#fff',
                            borderWidth: 1
                        },
                        emphasis: {
                            label: {
                                fontSize: 20
                            }
                        },
                        data: [
                            {value: 100, name: '展现'},
                            {value: 80, name: '点击'},
                            {value: 75, name: '访问'},
                            {value: 40, name: '咨询'},
                            {value: 20, name: '订单'}
                        ],
                    }
                ]
            };

            // 从后台拿数据
            $.get("/charts/queryTranCount", function (data) {
                optionForFunnel.legend.data = $(data).map(function () {
                    return this.name;
                }).get(); // 得到dom数组
                optionForFunnel.series[0].data = data;
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(optionForFunnel);
            }, "json");

        });



        // optionForBing = {
        //     // title: {
        //     //     text: '某站点用户访问来源',
        //     //     subtext: '纯属虚构',
        //     //     left: 'center'
        //     // },
        //     tooltip: {
        //         trigger: 'item',
        //         formatter: '{a} <br/>{b} : {c} ({d}%)'
        //     },
        //     legend: {
        //         orient: 'vertical',
        //         left: 'left',
        //         data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        //     },
        //     series: [
        //         {
        //             name: '访问来源',
        //             type: 'pie',
        //             radius: '55%',
        //             center: ['50%', '60%'],
        //             data: [
        //                 {value: 335, name: '直接访问'},
        //                 {value: 310, name: '邮件营销'},
        //                 {value: 234, name: '联盟广告'},
        //                 {value: 135, name: '视频广告'},
        //                 {value: 1548, name: '搜索引擎'}
        //             ],
        //             emphasis: {
        //                 itemStyle: {
        //                     shadowBlur: 10,
        //                     shadowOffsetX: 0,
        //                     shadowColor: 'rgba(0, 0, 0, 0.5)'
        //                 }
        //             }
        //         }
        //     ]
        // };
        //
        // myChart.setOption(optionForBing);


        // jQuery(function ($) {
        //     $.get("/charts/getTranCount.json", function (data) {
        //
        //         optionForBing.legend.data = $(data).map(function () {
        //             return this.name;
        //         }).get();
        //
        //         optionForBing.series[0].data = data;
        //
        //         // 使用刚指定的配置项和数据显示图表。
        //         myChart.setOption(optionForBing);
        //     }, "json");
        // });


    </script>
</head>
<body>
<div id="main" style="width: 1000px;height:500px;"></div>
</body>
</html>