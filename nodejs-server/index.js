var express = require('express');
var app = express();

app.use(express.static('dist'));

app.all('/hello', function (req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    var anObject = {
        message: 'hello'
    }
    console.log(req.headers);
    res.header('Content-Type', 'text/html');
    res.send(new Buffer('<h2>Test String</h2>'));
})

app.listen(3000)