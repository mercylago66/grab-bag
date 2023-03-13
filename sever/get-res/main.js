let http = require("http");
let ip = require("ip");
let download = require("download");
const querystring = require("querystring");

http.createServer(function (request, response) {

    let post = "";

    request.on('data', function (data) {
        handlerPos(data.toString());
        console.log(' data ', querystring.parse(data));
        console.log(' data ', data.toString());
    });

    request.on('end', function () {
        post = querystring.parse(post);
        response.end();
    });
}).listen(8888);

const handlerPos = function (url) {

    if (url.includes(".png")) {
        (async () => {
            try {
                await download(url, "./test");
            } catch (e) {
                console.error(`下载错误 ${e} : ${url}`);
            }
        })();
    }

}


// 终端打印如下信息
console.log(`服务器 运行中 http://${ip.address()}:8888/`);