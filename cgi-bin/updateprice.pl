#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;

print("Content-type: text/plain\n\n");
my @names = $query->param;
foreach $name (@names) {
    print($name) . " ";
    if ( $name =~ /\_/) { 
        next;
    } else {
        print "<p> ".$name."\t=\t".$query->param($name) . "</p>\n";
    }
}