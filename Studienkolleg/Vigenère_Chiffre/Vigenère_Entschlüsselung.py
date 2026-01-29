Unigram_Haeufigkeit = {
    'e': 17.4, 'n': 9.78, 'i': 7.55, 's': 7.27, 'r': 7.0, 'a': 6.51,
    't': 6.15, 'd': 5.08, 'h': 4.76, 'u': 4.35, 'l': 3.44, 'c': 3.06,
    'g': 3.01, 'm': 2.53, 'o': 2.51, 'b': 1.89, 'w': 1.89, 'f': 1.66,
    'k': 1.21, 'z': 1.13, 'p': 0.79, 'v': 0.67, 'j': 0.27, 'y': 0.04,
    'x': 0.03, 'q': 0.02
}

def Laenge_Schluessel_Identifizierung (Geheimtext):
    Zaehlung_Haeufigkeit = [0] * len(Geheimtext)
    for i in range (len(Geheimtext)):
        for j in range (len(Geheimtext) - i):
            if Geheimtext[j] == Geheimtext[i + j]:
                Zaehlung_Haeufigkeit[i] += 1
    Laenge_Schluessel = 0
    Wert_laenge_Schluessel = 0
    for i in range (1, len(Zaehlung_Haeufigkeit), 1):
        Wert = 0
        for j in range (i, len(Zaehlung_Haeufigkeit), i):
            Wert += Zaehlung_Haeufigkeit[j] * (len(Zaehlung_Haeufigkeit) - i) / len(Zaehlung_Haeufigkeit)
        Wert /= (len(Zaehlung_Haeufigkeit) // i + 1) 
        print (i, ' ', Wert)
        if Wert > Wert_laenge_Schluessel:
            Wert_laenge_Schluessel = Wert
            Laenge_Schluessel = i
    print(Laenge_Schluessel)
    return Laenge_Schluessel

def nach_index_verteilt(Geheimtext, Laenge_Schluessel, it):
    Menge = ""
    for i in range (it, len(Geheimtext), Laenge_Schluessel):
        Menge += Geheimtext[i]
    return Menge

def um_n_Stellen_verschoben(Menge, n):
    string = ""
    for i in range (len(Menge)):
        ascii = ord(Menge[i])
        if 97 <= ascii and ascii <= 122: 
            string += chr((ascii - 97 + n) % 26  + 97)
        elif 65 <= ascii and ascii <= 90: 
            string += chr((ascii - 65 + n) % 26  + 65)
        else:
            string += Menge[i]
    return string

def Schluessel_Identifizierung(nach_index_verteilt_Elemente):
    index = 0
    Wert = 0
    for i in range (26):
        tmp = um_n_Stellen_verschoben(nach_index_verteilt_Elemente, i)
        tmp_Wert = 0
        for j in range (len(tmp)):
            tmp_Wert += Unigram_Haeufigkeit.get(tmp[j], 0)
            #print (tmp[j], " ", tmp_Wert)
        #print (it, " ", i, " ", tmp_Wert)
        if tmp_Wert > Wert:
            Wert = tmp_Wert
            index = i
    return index

def Entschluesselung(Geheimtext, Schluessel):
    Schluessel_Laenge = len(Schluessel)
    Klartext = ""
    for i in range (len(Geheimtext)):
        ascii = ord(Geheimtext[i])
        ascii_Schluessel = ord(Schluessel[i % Schluessel_Laenge]) - 65
        if 97 <= ascii and ascii <= 122: 
            Klartext += chr((ascii - 97 - ascii_Schluessel) % 26  + 97)
        elif 65 <= ascii and ascii <= 90: 
            Klartext += chr((ascii - 65 - ascii_Schluessel) % 26  + 65)
        else:
            Klartext += Geheimtext[i]
    return Klartext

print("Geben Sie bitte den Geheimtext ein: ", end = "")
Geheimtext = input()
Laenge_Schluessel = Laenge_Schluessel_Identifizierung(Geheimtext)
Schluessel = ""
for it in range (Laenge_Schluessel):
    nach_index_verteilt_Elemente = nach_index_verteilt(Geheimtext, Laenge_Schluessel, it)
    index = Schluessel_Identifizierung(nach_index_verteilt_Elemente)
    Schluessel += chr((26 - index) % 26 + 65)
Klartext = Entschluesselung(Geheimtext, Schluessel)
print ("Hier ist wahrscheinlich der Schluessel:", Schluessel)
print ("Hier ist Klartext:", Klartext)
        
