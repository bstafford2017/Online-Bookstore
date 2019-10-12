#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
$query = new CGI;
$search = $query->param('search-box') if defined($search);
print("Content-type: text/html\n");

print <<EndofHTML;
  <html>
   <body>
    <table width="100%" height="80%">
     <tr>
      <td align="center">
       <font size="+0"><b>
EndofHTML

$search =~ s/^\s*(\S*)\s*$/$1/ if defined($search);
$search =~ s/;|>|>>|<|\*|\?|\&|\|//g if defined($search);

$cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom SearchBook";
$cmd = "$cmd $search" if defined($search);
system($cmd);

print <<EndofHTML;
        <form>
         <input type="button" value=" Back " onclick="history.go(-1);return false;" />
        </form>
       </b></font>
      </td>
     </tr>
    </table>
   </body>
  </html>
EndofHTML
