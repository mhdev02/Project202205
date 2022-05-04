# UnnamedProject


## 목적 

    지금까지 학습해왔던 내용을 프로젝트에 적용해보고 앞으로의 학습 방향에 대해 생각하는 계기를 만듦


## 기술 스택과 개발 환경 

    Java, Spring Boot, Jenkins, Docker, MySQL | Spring Tool Suite, Visual Studio Code


## 브랜치 관리 전략  

    feature 브랜치에서 main 브랜치로 merge


## 커밋 메시지

    FIX: 버그 수정
    CREATE: 새로운 기능 추가 등의 코드 작성
    UPDATE: 기존 코드 수정
    DELETE: 코드 파일 삭제

    Commit을 할 때 issue 번호(#1 등)를 포함하여 트래킹이 가능하도록 함


## 클린 코드 작성을 위한 노력

    기능과 관련된 네이밍 사용(변수 이름 등)
    메서드 이름은 동사, 동사구 사용
    클래스 이름은 명사, 명사구 사용
    테스트 코드 작성
    중복 제거 시도


## 주요 기능


## 회고(개선 사항에 대한 아이디어, 프로젝트 동안의 Pain Point 등)

    Jenkins, Github 연동, github webhook
    Jenkins를 로컬 컴퓨터의 docker container에서 실행하고 공유기에서 포트포워딩이나 ngrok을 사용해서 commit을 push할 때 github webhook이 작동한 것은 확인했으나, 
    같은 설정과 환경에서 Pull Request할 때는 github webhook에 의해 Jenkins의 Build가 작동하지 않음 --> Branches to build > Branch Specifier을 'any'로 하면 작동 됨