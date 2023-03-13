let http = require("http");
let ip = require("ip");
let download = require("download");
const querystring = require("querystring");

http.createServer(function (request, response) {

    let post = "";

    request.on('data', function (data) {
        // handlerPos(data.toString());
        console.log(' data ', data.toString());
    });

    request.on('end', function () {
        post = querystring.parse(post);
        response.end();
    });
}).listen(8888);
let url = "https://h5c.cqgame.games/h5/241/assets/images/common/symbol/clip/clip_H2.png"
let cookie = "_gid=GA1.2.1470804959.1678697207; _gat_gtag_UA_125722939_1=1; _gat_UA-125722939-1=1; _ga_73S0SCMJZB=GS1.1.1678697179.17.1.1678697207.32.0.0; _ga=GA1.2.1745364426.1678697207";

const handlerPos = function (url,cookie) {

    if (url.includes(".png")) {
        (async () => {
            try {
                // download.request.setHeader("cookie",cookie);
                await download(url, "./test");
            } catch (e) {
                console.error(`下载错误 ${e} : ${url}`);
            }
        })();
    }
}

handlerPos(url,cookie);

// 终端打印如下信息
console.log(`服务器 运行中 http://${ip.address()}:8888/`);