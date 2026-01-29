with open('Klartext.txt','r') as file:
    Klartext = file.read()
print("Geben Sie den Schluessel:", end = "")
Schluessel = input()
Schluessel_Laenge = len(Schluessel)
Geheimtext = ""
for i in range (len(Klartext)):
    ascii = ord(Klartext[i])
    ascii_Schluessel = ord(Schluessel[i % Schluessel_Laenge])
    if 65 <= ascii_Schluessel and ascii_Schluessel <= 90:
        ascii_Schluessel -= 65
    elif 97 <= ascii_Schluessel and ascii_Schluessel <= 122:
        ascii_Schluessel -= 97
    if 97 <= ascii and ascii <= 122: 
        Geheimtext += chr((ascii - 97 + ascii_Schluessel) % 26  + 97)
    elif 65 <= ascii and ascii <= 90: 
        Geheimtext += chr((ascii - 65 + ascii_Schluessel) % 26  + 65)
    else:
        Geheimtext += Klartext[i]
print("Hier ist den Geheimtext:", Geheimtext)
with open('Geheimtext.txt', 'w') as file:
    file.write(Geheimtext)


