# 깃 브런치를 다같이 이해해보아요~~~

지금까지 한 것들을 정리해보자면

repository = java_2023_4teampro가 우리조의 작업물을 저장하기 위해 "혜지"가 만든거임
    
    
  그리고 
  git remote add origin 주소
  
  즉,  git remote add origin https://github.com/claddds/java_2023_4teampro.git
  를 쳐서 원격저장소(github)와 각자 로컬저장소(본인의 컴퓨터)에 연결을 했습니다
  
  잘 연결되어있는지 확인하기 위해서
  연결된 폴더 git bush에 들어가
  
  git remote -v
  
  를 입력하면 잘 연결되어있는지 확인할 수 있슴돠
  
  
  ![image](https://github.com/claddds/java_2023_4teampro/assets/104687154/b52e3530-dc91-4df1-b8a0-9347d1abac43)
  
  branch를 생성하고 연결까지가 지금까지 한 것~~~~
  다들 브런치가 있어서 여기까지는 완료!!!!
  
  지금 현재 제가 제 브런치에서 수정을해서 브런치에 업로드를 하고 master까지 merge(병합)을 했어요(제것만)
  
  그러면 지금 MASTER에 올라와 있는 파일과 조원분들 이클립스에 실행되는 파일이 다른상태! -> 파일옆에 글자들이 다른 이유(다른것만 변경된것이다)!
  
  그러면 현재 MASTER에 있는 파일을 사용하기위해서는 
  로컬저장소(본인컴퓨터)에 파일을 끌어와야합니다.
  
  git pull
  
  를 사용하면 바뀐 데이터들을 받을 수 있어요. 안된다면,,,
  
  git pull origin master
  
  를 입력!
  
  그러면 로컬저장소(본인의 컴퓨터)에 파일들이 들어와있는 것을 볼 수 있어요
  
  ------------------------------------------------------------
  
  파일수정후 저장
  
  -------------------------------------------------------------
  
  git branch -r
  
  를 입력하면 현재 원격저장소에 있는 브런치를 확인할 수 있어요(저 위에 master인 경우, 본인의 이름이라면 본인 브런치에 들어와 있는 거라 건너뛰어도 됩니다.)
  
  
  ![image](https://github.com/claddds/java_2023_4teampro/assets/104687154/7836b5d7-5668-44fe-9766-8ecfc61ba320)
  
  (master)인 경우 본인 브런치가 있는 지 확인하고 
  
  git checkout 본인이름
  
  를 사용하여 본인의 브런치안으로 들어옵니다.
  
  
  ![image](https://github.com/claddds/java_2023_4teampro/assets/104687154/6477a909-9964-433f-aa1f-cabcae133469)

git add . 

git commit -m "구분할 수 있는 메세지"

를 사용해 브런치를 추가하고 커밋을 한 후에

![image](https://github.com/claddds/java_2023_4teampro/assets/104687154/db08b9c2-a34a-4cc3-bb2f-3645a23612fd)

git push origin 본인 브런치 이름


![image](https://github.com/claddds/java_2023_4teampro/assets/104687154/d84f6e06-46f8-4ff3-b9b5-eb0dcbb8467c)

를 사용하여 본인의 브런치에 결과물을 저장할 수 있습니다!!

그럼 끄읏~~~
반박시 본인이 맞음
그럼 뿅
