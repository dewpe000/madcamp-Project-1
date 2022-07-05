# 

# Frame

## 1. **splash screen**

- splash thread와 여러 animation들을 이용해 앱 시작 시 애니메이션을 포함한 splash screen이 뜨도록 설정되었다.
- alpha_down: 투명도를 1부터 0으로 변화되도록 함

```xml
<?xml version="1.0" encoding="utf-8"?>
<alpha
    xmlns:android="<http://schemas.android.com/apk/res/android>"
    android:startOffset = "1500"
    android:fromAlpha="1.0"
    android:toAlpha="0.0"
    android:duration="750"
    android:fillAfter = "true"/
```

- alpha_up: 투명도를 0부터 1로 변화하도록 함

```xml
<?xml version="1.0" encoding="utf-8"?>
<alpha
    xmlns:android="<http://schemas.android.com/apk/res/android>"
    android:startOffset = "1700"
    android:fromAlpha="0.0"
    android:toAlpha="1.0"
    android:duration="700" />
```

## 2. **main frame**

- 3개의 fragment를 이용하여 tab 1, tab 2, tab 3간의 전환을 가능하게 하였다.
- fragment간의 전환은 ViewPager2와 Bottom Navigation을 이용하여 전환한다.

# Tab 1

- Tab 1는 Custom Gallery application이다.
1. **upload**
    
  ![KakaoTalk_20220705_203812991_13](https://user-images.githubusercontent.com/108382134/177321700-7d00b557-06f7-4f90-8df8-76fc95e9ea29.png)

    
    - 아래 방향으로 스크롤하여 새로고침하면, 휴대전화 내부의 연락처와 연동하여 이름, 전화번호, 프로필 사진을 가져온다.
    - ContentResolver를 통해 스마트폰의 연락처 정보(이름, 전화번호, 프로필 사진 등)를 가져온다.
    - 가져온 연락처는 RecyclerView를 이용하여 Grid 형식으로 표현된다.
2. **add**
    
   ![KakaoTalk_20220705_203812991_15](https://user-images.githubusercontent.com/108382134/177321719-bdf3b662-8480-4f9b-bb1e-61b4c929533d.png)

    
    - 우측 하단의 플로팅 액션 버튼을 누르면 새로운 연락처를 추가할 수 있다.
    - 새로운 Activity를 띄운 후 연락처와 전화번호를 EdiText를 통해 받는다.
    - 프로필 사진을 설정하고 싶은 경우, Tab2에서 이용한 방식을 이용하여 스마트폰의 저장소에서 받아온다.
    - 사용자의 입력을 Tab1 Fragment에 전달한다.
3. **delete**
    - long click 시 팝업창을 통해 해당 연락처를 삭제할지 물어보고 ‘확인'을 선택 시 해당 연락처를 삭제한다.
4. **call & message**
    
    []()
    
    - 연락처 정보를 선택하면 아래가 확장하며 전화와 메세지 아이콘이 표시된다.
    - 📞 아이콘을 선택하면 해당 연락처의 키패드로 연결되어 전화할 수 있도록 하고, ✉️ 아이콘을 선택하면 해당 연락처의 메세지 창과 연결되어 간단하게 메세지를 보낼 수 있도록 한다.

# Tab 2

- Tab 2는 Custom Gallery application이다.
    1. **upload**
        
     ![KakaoTalk_20220705_203812991](https://user-images.githubusercontent.com/108382134/177321759-9b0fefd1-ea30-42f7-838c-bc4d1e7cba59.png)

        
    - 우측 하단의 플로팅 액션 버튼을 클릭 시 휴대전화 내부의 갤러리 어플과 연동하여 사용자가 선택한 사진을 불러온다.
    - 가져온 이미지는 RecyclerView를 이용하여 Grid 형식으로 표현된다.
    2. **delete**
    - long click 시 팝업창을 통해 해당 사진을 삭제할 지 물어보고, ‘확인'을 선택 시 해당 사진을 삭제한다.
    3. **slide Image**
        
      ![KakaoTalk_20220705_203812991_01](https://user-images.githubusercontent.com/108382134/177321793-329e5022-0523-408c-aec9-9da92cde666a.png)

        
        - Grid 형식의 이미지를 클릭 시 상단에 PhotoVIew가 나타나 큰 사진을 볼 수 있도록 돕는다.
        - slide를 하는 동안에도 하단의 Gird를 통해 사진 간 전환이 가능하다.
        - 이미지 뷰어 하단의 margin을 슬라이드 하는 것을 통해 뷰어의 사진을 좌우로 전환할 수 있다. slide를 하는 동안에도 하단의 gird를 통해 사진 간 전환이 가능하다.
        - 각 PhotoView는 Fragment이며, ViewPager2를 활용하여 이미지를 넘기며 볼 수 있다.
    4. **Zoom**
        
       ![KakaoTalk_20220705_203812991_02](https://user-images.githubusercontent.com/108382134/177321840-8d0a178c-3d07-43af-9da1-c605a5859993.png)

        
        - PhotoView에서 두 손가락을 이용한 제스처를 인식해 확대 및 축소할 수 있다.
        - 
    

# Tab3

- Tab 3는 명확한 의사결정을 위한 application이다.
- 4개의 Activity에 각자 다른 게임들을 구현하였다.

## 1. Roulette

![KakaoTalk_20220705_203812991_04](https://user-images.githubusercontent.com/108382134/177321875-ff58a325-a191-466e-9bdc-f76a62119a9f.png)


- Roulette은 add, start, restart 버튼으로 구성된 룰렛 어플이다.
1. **add**
- 입력칸에 추첨 대상을 입력하고 add 버튼을 이용해 룰렛에 추첨 대상이 추가된다.
2. **start**
- 추첨 대상이 모두 입력된 상태에서 start 버튼을 클릭하면 룰렛이 돌아가며 대상이 추첨된다.
- [github.com/mmoamenn](http://github.com/mmoamenn%EC%9D%98)의 github에서 제공하는 luckyWheel library를 활용해 구현하였다.
3. **restart**
- 우측 상단의 restart 버튼을 클릭하면 전체 게임을 초기화한다.

## 2. Lottery

1. **add**
    
 ![KakaoTalk_20220705_203812991_05](https://user-images.githubusercontent.com/108382134/177321896-404fe8e8-9803-48ad-a6fb-3019bb9f333e.png)

    
- add 버튼을 클릭하면 추첨에 사용될 제비의 수를 1개씩 증가시킨다.
2. **finish**
- 추첨에 사용할 수만큼의 제비가 추가되었을 때 finish 버튼을 누르면 add 버튼이 open으로 바뀌며 ‘당첨'인 제비의 수를 입력받는다. finish를 한 상태에서만 제비를 추첨할 수 있으며, 입력한 수만큼의 당첨 제비가 설정된다.
3. **open**
    
  ![KakaoTalk_20220705_203812991_06](https://user-images.githubusercontent.com/108382134/177321914-20fbb8c7-2dfb-4462-b112-be6a4bbb1b68.png)

- 제비를 터치하면 PASS FAIL 여부를 알 수 있다.
- 각각의 제비는 Visiblity가 GONE인 TextView를 가진다. 제비가 터치되면 TextView의 Visiblity가 Visible이 되며, TextView의 text의 통해 이미지를 변경한다.
- open 버튼을 클릭해서 모든 제비의 추첨 결과를 한번에 확인할 수 있다.
4. **restart**
- 우측 상단 restart 버튼을 클릭하면 전체 게임을 초기화한다.

## 3. Draw

1. add
    
  ![KakaoTalk_20220705_203812991_07](https://user-images.githubusercontent.com/108382134/177321947-165a03b8-2350-4702-8b7d-b71576f73f60.png)

    
    - 좌측 하단 add 버튼을 클릭해서 draw에 추가될 항목을 추가할 수 있다.
    - 추가된 항목들은 recyclerview를 이용해 화면 하단에 표시된다.
2. roll
    - 하단 중앙의 roll 버튼을 클릭하면 추첨할 인원을 설정할 수 있으며, 추첨할 인원을 입력하고 ‘확인' 버튼을 눌러 당첨자를 추첨한다.
    - 팝업창은 AlertDialog.Builder를 이용해 구현되었다.
3. restart
    - 우측 상단의 restart 아이콘을 클릭하면 전체 게임을 초기화할 수 있다.
4. get from contact
    
![KakaoTalk_20220705_203812991_08](https://user-images.githubusercontent.com/108382134/177321978-f0351873-1443-4573-bd78-66822ade6136.png)

    
    - 우측 상단 get from contact 버튼을 클릭하면 custom contact로부터 인원을 선택해 불러올 수 있다.
    - Tab 1의 fragment로부터 contact list를 arraylist 형태로 받고, 이를 AlertDialog.Builder를 이용해 팝업창에 띄우고, 이를 serMultiChoiceItems() 함수를 이용해 원하는 값만 선택해 추가할 수 있도록 하였다.

## 4. RSP

- RSP는 두 명의 참가자가 참여하는 가위바위보 게임이다.
1. **select**
    
   
    ![KakaoTalk_20220705_203812991_10](https://user-images.githubusercontent.com/108382134/177322005-e02e0d50-a06f-4970-b3fc-2ea99b6cdd1f.png)

- 화면 양쪽의 이미지를 클릭하면 팝업창을 통해 가위, 바위, 보 혹은 random을 선택할 수 있다. 자신이 원하는 값을 선택할 수도 있고, random 버튼을 클릭하면 3가지 값 중 랜덤하게 선택된다.
- imageView를 클릭하면 OnClickListener() 함수로 입력을 받아 팝업창을 띄운다. AlertDialog.Builder()의 setSingleChoiceItems를 이용해 3개의 rock, scissors, paper와 random을 선택하고, random 선택 시 3개 중 1개를 random.nextInt()를 이용해 선택할 수 있도록 했다.
2. **play**
![KakaoTalk_20220705_203812991_11](https://user-images.githubusercontent.com/108382134/177322037-470dda12-4942-4f01-8fb2-9a2e58305f81.png)

- play 버튼을 누르면 선택된 값을 바탕으로 가위바위보를 진행해서 승패를 결정한다.
- Visibility를 설정해 play 버튼을 누르면 again 버튼으로 바뀌고, 이 버튼을 누르면 게임이 처음으로 돌아온다.

**개발 팀원**

- 한양대학교 컴퓨터소프트웨어학부 한관희
- KAIST 전산학부 신의진

**개발 환경**

- OS: Android
- Language: Java
- IDE: Android Studio
- Target Device: Galaxy S22
