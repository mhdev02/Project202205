const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const compression = require('compression');

const processRouter = require('./router');

const app = express();

app.use(cors());
app.use(compression());
app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

app.use('/api/process', processRouter);

app.listen(9000, () => {
    console.log('Listening on port 9000');
});

module.exports = app;