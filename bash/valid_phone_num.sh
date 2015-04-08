#!/bin/bash
#
#Given a text file file.txt that contains list of phone numbers (one per line), write a one liner bash script to print all valid phone numbers.
#
#You may assume that a valid phone number must appear in one of the following two formats: (xxx) xxx-xxxx or xxx-xxx-xxxx. (x means a digit)
#
#You may also assume each line in the text file must not contain leading or trailing white spaces.
#
#For example, assume that file.txt has the following content:
#
#987-123-4567
#123 456 7890
#(123) 456-7890
#
#NOTE: the following lines can only be copy/pasted into the command line. 
# they could not be run in a bash script because the regex conflicts with 
# bash syntax, and the are are not properly escaped in the bash script
#
#Your script should output the following valid phone numbers:
#987-123-4567
#(123) 456-7890
#
#solution using grep
grep -P '^(\d{3}-|\(\d{3}\) )\d{3}-\d{4}$' valid_phone_num.input
#
#solution using sed
sed -n -E '/^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$/p' valid_phone_num.input
#
#solution using awk
awk '/^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$/' valid_phone_num.input
