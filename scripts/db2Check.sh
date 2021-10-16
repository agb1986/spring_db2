db_name="$(db2 list db directory  | grep "Database name")";

echo $db_name

COUNTER=20
until [  $COUNTER -lt 0 ]; do
    for db in $db_name
    do
        echo $db
        if [$db == *"SAMPLE"*];
            then
                echo "DB PRESENT"
                let COUNTER=0
            else
                echo "DB NOT PRESENT"
                sleep 2;
                echo "COUNTER: $COUNTER"
                let COUNTER-=1
        fi
    done
done