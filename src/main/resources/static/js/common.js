/**
 * 判断是否为pc端
 * @returns {boolean}
 */
function isPC() {
    return !/(iPhone|iPad|iPod|iOS|Android)/i.test(navigator.userAgent);
}

/**
 * 判断是否登录
 * @returns {boolean}
 */
function checkLoginStatus() {
    post("checkLoginStatus").then(res => {
            const code = res.code;
            if (code !== 0) {
                window.location.href = "/login";
            }
        },
        error => {
            console.log(error);
        });
}

function checkLogin() {
    let flag = false;
    post("checkLoginStatus").then(res => {
            const code = res.code;
            if (code !== 0) {
                flag = false;
            }
            flag = true;
        },
        error => {
            console.log(error);
            flag = false;
        });
    return flag;
}

function checkOrder() {

}


/**
 * 带参数发送post请求
 * @param service
 * @param params
 * @returns {result}
 */
function post(service, params) {
    params = params === undefined || params === null ? {"type": service} : params;
    return axios.post("/api/" + service, params).then(function (response) {
        return response.data;
    }).catch(function (error) {
        console.error("请求失败", error);
    });
}

/**
 * 获取URL中的参数名及参数值的集合
 * @param {[string]} urlStr [当该参数不为空的时候，则解析该url中的参数集合]
 * @return {Object}       [参数集合]
 */
function GetRequest(urlStr) {
    if (typeof urlStr == "undefined") {
        var url = decodeURI(location.search); //获取url中"?"符后的字符串
    } else {
        var url = "?" + urlStr.split("?")[1];
    }
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

function printTable() {
    Print('#print', {'no-print': '.no-print'});
}

function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() + ' ';
    return Y + M + D;
}