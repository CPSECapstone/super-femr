#!/bin/bash

# Values... adjust as needed or move around
ROOTDIR=../../../../
now=$(date +"%m_%d_%Y")
MYSQL_HOST='localhost'
MYSQL_PORT='3306'
MYSQL_USER='root'
MYSQL_PASSWORD='password'
DATABASE_NAME='femr_db'


# Creates a backup of the existing MySQL database
. $ROOTDIR/VERSION

mkdir -p $ROOTDIR/sqlbackups

# Backing up local database. REQUIRES VERSION FILE
mysqldump -h ${MYSQL_HOST} \
          -P ${MYSQL_PORT} \
          -u ${MYSQL_USER} \
          -p${MYSQL_PASSWORD} \
          ${DATABASE_NAME} | gzip > $ROOTDIR/sqlbackups/$now-femr_backup-$VER.sql.gz
# mysqldump \
#     --databases femr \
#     --master-data=2  \
#     --single-transaction \
#     --order-by-primary \
#     -r $now-rollbackBackupVer-$VER.sql
# Compress backup file to conserve resources.
