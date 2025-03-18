You need to execute 2 commands to start-up the application that is going to be tested:
```
docker build -t backendtest .
```
```
docker run -p 5000:5000 backendtest
```