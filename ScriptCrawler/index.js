const express = require('express');
const bodyParser = require('body-parser');

const processRouter = require('./router');

const app = express();

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

app.use('/api/process', processRouter);

app.listen(9000, () => {
    console.log('Listening on port 9000');
});