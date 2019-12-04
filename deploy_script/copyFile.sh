#!/usr/bin/expect -f

spawn scp DDL/linkage.sql linkage@118.25.191.207:/home/linkage/sql/linkage.sql
expect {
  -re ".*es.*o.*" {
    exp_send "yes\r"
    exp_continue
  }
  -re ".*sword.*" {
    exp_send "linkage\r"
  }
}
interact