with open('Klartext.txt','r') as file:
    Klartext = file.read()
print("Geben Sie den Schluessel:", end = "")
Schluessel = int(input())
Geheimtext = ""
for i in range (len(Klartext)):
    ascii = ord(Klartext[i])
    if 97 <= ascii and ascii <= 122: 
        Geheimtext += chr((ascii - 97 + Schluessel) % 26  + 97)
    elif 65 <= ascii and ascii <= 90: 
        Geheimtext += chr((ascii - 65 + Schluessel) % 26  + 65)
    else:
        Geheimtext += Klartext[i]
print("Hier ist den Geheimtext:", Geheimtext)
with open('Geheimtext.txt', 'w') as file:
    file.write(Geheimtext)

