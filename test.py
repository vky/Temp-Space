import urllib2
import json

file = open("stats.txt", "w")

host = "http://us.battle.net"
realm = "Stonemaul"
guild = "Cuties"
URL = host + "/api/wow/guild/" + realm + "/" + guild + "?fields=members"

try:
    data = urllib2.urlopen(URL).read()
except urllib2.HTTPError, e:
    print "HTTP error: %d" % e.code
except urllib2.URLError, e:
    print "Network error: %s" % e.resason.args[1]

decoded = json.loads(data)

print "Member count: " + str(len(decoded["members"]))
file.write( "Member count: " + str(len(decoded["members"])))
print "\nLevel 85 members:"
file.write( "\nLevel 85 members:\n")
for char in decoded["members"]:
    if char["character"]["level"] == 85:
        try:
            character = char["character"]["name"]
            charURL = host + "/api/wow/character/" + realm + "/" + character + "?fields=stats"
            try:
                chardata = urllib2.urlopen(charURL).read()
            except urllib2.HTTPError, e:
                print "HTTP error: %d" % e.code
            except urllib2.URLError, e:
                print "Network error: %s" % e.resason.args[1]

            stats = json.loads(chardata)
            print char["character"]["name"] + " : " + str(stats["stats"]["resil"])
            file.write(char["character"]["name"] + " : " + str(stats["stats"]["resil"]) + '\n') 
        except UnicodeEncodeError:
            print "UNICODE_ERROR"
            file.write("UNICODE_ERROR" + '\n')
file.close()
