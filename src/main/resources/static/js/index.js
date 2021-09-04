new Vue({
    el: "#boss",
    data: {
        echarts: ""
    },
    methods: {
        initMap() {
            post("getMap").then(res => {
                require([
                    'echarts',
                    'json/china-new.json'
                ], function (echarts, chinaJson) {
                    echarts.registerMap('china', chinaJson);
                    let option = {
                        visualMap: {
                            left: 'right',
                            min: 0,
                            max: 10,
                            inRange: {
                                color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
                            },
                            text: ['High', 'Low'],           // 文本，默认为数值文本
                            calculable: true
                        },
                        title: {
                            text: '客户热力图',
                            left: 'center'
                        },
                        roam: false,
                        series: {
                            type: 'map',
                            map: 'china',
                            label: {
                                normal: {
                                    show: true, // 是否显示对应地名
                                    textStyle: {
                                        color: "#000000",
                                    },
                                },
                            },
                            data: res.data,
                            tooltip: {
                                formatter(value) {
                                    console.log(value);
                                    return value.data.name + "<br/>" + "设备数：" + "22";
                                },
                                show: true
                            },
                            textFixed: {
                                Alaska: [20, -20]
                            },
                        }
                    };
                    testHelper.create(echarts, 'map', {
                        height: 800,
                        option: option
                    });
                });
            });
        },
        initZong() {
            post("getTop").then(res => {
                let xData = [];
                let yData = [];
                for (key in res.data) {
                    xData.push(res.data[key]['user_area']);
                    yData.push(res.data[key]['cnt']);
                }
                require(['echarts'], function (echarts) {
                    let option = {
                        xAxis: {
                            type: 'category',
                            data: xData
                        },
                        yAxis: {
                            type: 'value'
                        },
                        title: {
                            text: '销售前十地区',
                            left: 'center'
                        },
                        series: [{
                            data: yData,
                            type: 'bar',
                            showBackground: true,
                            backgroundStyle: {
                                color: 'rgba(180, 180, 180, 0.2)'
                            }
                        }]
                    };
                    testHelper.create(echarts, 'zong', {
                        height: 400,
                        option: option
                    });
                });
            });
        },
        initProduct() {
            post("getProduct").then(res => {
                require(['echarts'], function (echarts) {
                    let option = {
                        title: {
                            text: '商品销售比例',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                        },
                        series: [
                            {
                                name: '商品销售',
                                type: 'pie',
                                radius: '50%',
                                data: res.data,
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };
                    testHelper.create(echarts, 'zong', {
                        height: 400,
                        option: option
                    });
                });
            }, error => {

            });
        },
        initLine() {
            post("getLine").then(res => {
                let legends = res.data.productNames;
                let xAxis = res.data.months;
                require(['echarts'], function (echarts) {
                    let option = {
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: legends
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        toolbox: {
                            feature: {
                                saveAsImage: {}
                            }
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: xAxis
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: res.data.lines
                    };
                    testHelper.create(echarts, 'line', {
                        height: 400,
                        option: option
                    });
                });
            }, error => {

            });
        }
    }, mounted() {
        this.initMap();
        this.initZong();
        this.initProduct();
        this.initLine();
    }
});