#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;

print("Content-type: text/plain\n\n");
my @names = $query->param;
foreach my $name (@names) {
    print($name);
    print "<p> " . $name . "\t=\t" . $query->param($name) . "</p>\n";
}