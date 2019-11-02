#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;
my $search = $query->param('search-box');

$search =~ s/^\s*(\S*)\s*$/$1/ if defined($search);
$search =~ s/;|>|>>|<|\*|\?|\&|\|//g if defined($search);

print("Content-type: text/html\n\n");

my $compile = "/usr/bin/javac Search.java";
system($compile);

my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Search";
$cmd = "$cmd $search" if defined($search);
system($cmd);