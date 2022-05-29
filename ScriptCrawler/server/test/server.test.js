const request = require('supertest');
const app = require('../index');

let server;

beforeAll((done) => {
    server=app.listen(done);
});

describe('ScriptCrawler/server Testing', () => {
    test('POST /api/process/crawl without body', async() => {
        const response=await request(server).post("/api/process/crawl")
                                            .set('Accept', 'application/json')
                                            .type('application/json')
                                            .send({ "url1": "", "url2": "" })
        
        expect(response.status).toBe(400);
    });

    // test('POST /api/process/crawl with body', async() => {
    //     const response=await request(server).post("/api/process/crawl")
    //                                         .set('Accept', 'application/json')
    //                                         .type('application/json')
    //                                         .send({ "url1": "https://www.seoul.co.kr/", "url2": "https://www.hani.co.kr/" })
        
    //     expect(response.status).toBe(201);
    //     expect(response.body).not.toBeNull();
    //     // expect(response.body.length).toBeGreaterThanOrEqual(0);
    // });
});

afterAll((done)=> {
    server.close(done);
});