mvn package -B -DskipTests
docker build -t javajinx/game:v1 .
docker push javajinx/game:v1
cd kubernetes
# 19라인의 이미지 이름을 빌드한 이름에 맞도록 수정/저장한다.
kubectl apply -f ./
kubectl get all
