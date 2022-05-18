# Project202205


## 목적 

    지금까지 학습해왔던 내용을 프로젝트에 적용해보고 앞으로의 학습 방향에 대해 생각하는 계기를 만듦
    따라서 보통 PR할 때 해당 부분에 대한 회고도 업데이트 함(협업을 가정한 문서화 작업)


## 기술 스택과 개발 환경 

    기술 스택: Java, Spring Framework, Javascript, Node.js, MySQL
    개발 환경: Spring Tool Suite, Visual Studio Code


## 아키텍쳐

<img width="843" alt="Screen Shot 2022-05-18 at 3 03 58 AM" src="https://user-images.githubusercontent.com/62423408/168880289-a7665233-86dc-4e3c-820f-3ca77090871a.png">


## 브랜치 관리 전략  

    feature 브랜치에서 main 브랜치로 merge


## 커밋 메시지 타입

    FIX: 버그 수정, 오타 등의 간단한 수정 
    CREATE: 새로운 기능 추가 등의 코드 작성
    UPDATE: 기존 코드 수정
    DELETE: 코드 파일 삭제

    Commit을 할 때 issue 번호(#1 등)를 포함하여 트래킹이 가능하도록 함


## 클린 코드 작성을 위한 노력

    기능과 관련된 네이밍 사용(변수 이름 등)
    메서드 이름은 동사, 동사구 사용
    클래스 이름은 명사, 명사구 사용
    테스트 코드 작성
    중복된 코드 제거 시도


## 주요 기능 및 특징

    DevOps

        - Terraform으로 AWS EC2(t2.micro) 두 개를 운영(Elastic IP를 쓰지 않아 재부팅마다 public IP가 변경되므로 프로젝트 동안은 종료시키지 않음)
        

    Backend(Spring Framework)

        - 일반적인 프로그램 구현을 목적으로 함
        - 회원 가입 기능과 로그인 시 JWT가 발행되도록 구현
        

    ScriptCrawler(Node.js, Redis)

        - 두 개의 사이트에서 공통으로 등장하는 키워드들을 "키워드: 등장 횟수"의 형식으로 응답하도록 코드 작성(예를 들어, 신문사 사이트들에서 공통으로 등장하는 단어는 주목할만한 단어라고 가정하는 아이디어에서 시작함)
        - 로그인 기능은 없는 대신에 rate limit 로직을 추가해서 15초 동안 최대 20회의 요청만 보낼 수 있도록 하여 서버 성능(t2.micro) 범위 내에서 작동되도록 시도
        (apache benchmark를 사용해서 확인해보니 동시 요청 21회부터 요청 실패가 나는 것을 확인하고 15초로 수정. 아래 터미널 스크린샷 사진에서 확인 
        - https://erangad.medium.com/load-testing-a-rest-api-using-post-requests-6b0338196af0)
        - POST 요청에서 { "url1": "https://www.seoul.co.kr/", "url2": "https://www.hani.co.kr/" }을 body 값으로 주면 작동이 상대적으로 잘 됨을 확인
        - (Pub/Sub 패턴) 두 개의 Container(Tomcat 서버, Node.js 서버)가 한 개의 EC2(t2.micro) 서버에서 작동하는 구조에서 Redis를 이용해서 공통 키워드를 찾는 로직은 다른 EC2(t2.micro)에서 실행하도록 하여 성능 부하가 일어나지 않도록 시도

<img width="500" alt="Screen Shot 2022-05-19 at 12 18 39 AM" src="https://user-images.githubusercontent.com/62423408/169086200-44c0a01c-b191-48e6-8704-095daa8d8e73.png">


## 회고(개선 사항에 대한 아이디어, 프로젝트 동안의 Pain Point 등)

    
    Jenkins, Github 연동을 위해 Jenkins를 로컬 컴퓨터의 docker container에서 실행하고 공유기에서 포트포워딩이나 ngrok을 사용해서 commit을 push할 때 github webhook이 작동한 것은 확인했으나, 
    같은 설정과 환경에서 Pull Request할 때는 github webhook에 의해 Jenkins의 Build가 작동하지 않음 
    
        Manage Jenkins > Configure System > GitHub > GitHub Server에서 Credentials를 GitHub Access token을 이용해서 설정
        Jenkins job 설정에서 General > GitHub Project > Project url 설정
                               Build Triggers > GitHub Pull Requests(GitHub Integration Plugin 설치 필요)
                                              > Trigger Mode: Hooks  with Persisted Data
                                              > Trigger Events: Pull Request Opened
                               Pipeline  > Branch Specifier: feature**
        위와 같이 설정하면 feature로 시작하는 브랜치가 Pull Request될 때 jenkins job이 build 된다는 것을 확인함


    ScriptCrawler를 만들 때, request가 비동기로 작동한다고 해서 await request()와 같이 사용했더니 request() 내부에서 불러온 값이 request() 밖에서 선언된 변수에 저장되지 않았음
    
        await는 Promise와 같이 쓴다는 부분을 알게 되어 request()를 Promise로 감싸주는 또 다른 함수를 생성해서 해결


    ScriptCrawler가 두 개의 검색 포털, 신문 등에서 공통으로 등장하는 키워드를 뽑도록 만들어졌지만, 사이트 조합에 따라 키워드가 2~3개 정도만 나오는 경우도 있음
    서울 신문(https://www.seoul.co.kr), 한겨레 신문(https://www.hani.co.kr), 경향 신문(https://www.khan.co.kr/)에서 테스트 해봄


    TDD 방식으로 개발하면 Postman으로 매번 기능을 확인할 필요가 없게 됨을 알게 됨. 테스트 케이스가 일종의 기능 명세서 역할을 하고 어플리케이션의 품질을 높인다고 함


    ScriptCrawler/server/crawlController.js에서 rate limit을 5분 동안 최대 20회의 요청으로 설정하는 코드를 작성하기로 하고 테스트는 5분 동안 최대 1회의 요청만 되도록 실시해보니 
    "Error [ERR_HTTP_HEADERS_SENT]: Cannot set headers after they are sent to the client"라는 에러가 발생했고 이는 응답을 두 번 보내려고 할 때 발생한다는 사실을 확인하고 
    rate limit 초과 시 발생하는 response에 return을 명시하여 해결 함.


    Docker container에서 ScriptCrawler/worker를 실행하고 redis-server는 EC2 host에서 실행하다보니 container가 host와 통신할 수 있게 설정이 필요했음
    https://www.howtogeek.com/devops/how-to-connect-to-localhost-within-a-docker-container/

        docker run -d --network=host my-container:latest
        (Now your container can reference localhost or 127.0.0.1 directly)

    
    Nginx를 EC2 host에 설치하고 jenkins는 docker container로 실행시킬 때 Nginx를 통해 proxy_pass로 jenkins container로 넘어가지 않아 5시간 정도 해결책 조사 및 여러 시도를 거쳐 보류하기로 함. 
    Nginx, Jenkins는 개별적으로 동작함을 확인
    시니어 혹은 사수가 있으면 좋겠다는 생각을 하면서 stackoverflow에 관련 질문을 올려놓음
    (https://stackoverflow.com/questions/72278474/nginx-on-ec2-host-jenkins-on-docker-container)