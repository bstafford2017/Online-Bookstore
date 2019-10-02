#!/usr/bin/perl
use CGI; 
$query = new CGI;
$search = $query->param('search-box');

$search =~ s/^\s*(\S*)\s*$/$1/;
$search =~ s/;|>|>>|<|\*|\?|\&|\|//g;

$cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Search ";
$cmd .= join $search, " ";

system($cmd);