C:\kafka\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 3 --partitions 2 --topic twitter-two  --config min.insync.replicas=2