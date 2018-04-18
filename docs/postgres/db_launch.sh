docker run --name postgresql -itd --restart always \
  --publish 5432:5432 \
  --volume /Users/<username>/postgres_stuff/data:/var/lib/postgresql \
  --env 'PG_TRUST_LOCALNET=true' \
  sameersbn/postgresql:9.6-2


#  --env 'DB_USER=test' --env 'DB_PASS=test' \
#  --env 'DB_NAME=jdatabase' \
