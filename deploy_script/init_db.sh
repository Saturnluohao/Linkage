#!/usr/bin/expect

set sql_path [lindex $argv 0]

spawn mysql -u linkage -p
expect "password"
send "@Cmhdb_wsngp6\r"
expect "mysql>"
send "source $sql_path;\r"
expect eof
exit
