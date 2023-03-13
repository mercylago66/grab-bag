let fs = require("fs");
let request = require("request");

let url = "https://h5c.cqgame.games/h5/231/assets/images/common/symbol/clip/clip_win.png?cbe3c79"
// let url = "https://h5c.cqgame.games/h5/241/assets/images/common/symbol/clip/clip_W.json?f0474a1"
// let cookie = "_gid=GA1.2.1470804959.1678697207; _gat_gtag_UA_125722939_1=1; _gat_UA-125722939-1=1; _ga_73S0SCMJZB=GS1.1.1678697179.17.1.1678697207.32.0.0; _ga=GA1.2.1745364426.1678697207";
let cookie = "_gid=GA1.2.1470804959.1678697207; _gat_gtag_UA_125722939_1=1; _gat_UA-125722939-1=1; _ga_73S0SCMJZB=GS1.1.1678697179.17.1.1678697207.32.0.0; _ga=GA1.2.1745364426.1678697207";

const getPng = (url, cookie) => {
    request({url}).setHeader("cookie", cookie);
    request({url}).pipe(fs.createWriteStream("./test/clip_win.png").on('close', err => {
        console.log('下载成功!', err);
    }));
}
getPng(url, cookie);
