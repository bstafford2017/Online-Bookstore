#!/usr/bin/perl
use strict;
use CGI; 
my $query = new CGI;
my $search = $query->param('search-box');

if(!defined $search){
    exit(0);
}

$search =~ s/^\s*(\S*)\s*$/$1/;
$search =~ s/;|>|>>|<|\*|\?|\&|\|//g;

print("Content-type: text/html\n\n");

my $compile = "/usr/bin/javac Search.java";
system($compile);

my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Search ";
$cmd = $cmd . $search;
system($cmd);