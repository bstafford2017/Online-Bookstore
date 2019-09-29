#!/usr/bin/perl
use CGI; 
$query = new CGI;
$act = $query->param('act');
$author = $query->param('author');

$author =~ s/^\s*(\S*)\s*$/$1/;
$author =~ s/;|>|>>|<|\*|\?|\&|\|//g;

$cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom ListTitles ";
system($cmd);