## BE-onz-data

## 사전 작업
1. 실행 중인 컴퓨터에서 사용하고 있는 Chrome과 동일한 버전의 ChromeDriver 설치
   [Chrome for Testing 다운로드 링크](https://googlechromelabs.github.io/chrome-for-testing/)
2. `seoul-region.sql` 실행 -> 주소 구분 테이블 데이터 입력
   ![image](https://github.com/user-attachments/assets/16fdcea2-1a8d-44a0-a062-0b991552d8e1)

## 요청
### 로컬
```
localhost:8080/api/location/kakao?province={서울시}&district={종로구}&subdistrict={혜화동}&keyword={칵테일바}
```
- 현재로서는 1페이지만 스크래핑하도록 작성되어 있음

## 데이터 예시
### Bar
![image](https://github.com/user-attachments/assets/57c14ed8-49d3-4d5b-b5f5-60e5a10762a5)

### BarImage
![image](https://github.com/user-attachments/assets/aa66d26c-fea7-43f7-a2f0-dbee20844bad)

### Menu
![image](https://github.com/user-attachments/assets/3cee15ca-4b9c-4d06-a3cd-dd5966b3a768)

### OpenHour
![image](https://github.com/user-attachments/assets/756e970c-8ad5-49fc-b8f5-661e813e72d7)

## 패키지 구조
<img width="417" alt="image" src="https://github.com/user-attachments/assets/18aeafa4-d425-4535-b958-1460146d821f" />
