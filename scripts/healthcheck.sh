docker cp ./db2Check.sh db2:.;
docker exec -it db2 bash -c $'su - db2inst1 -c \'cd ../../../; sh db2Check.sh\''