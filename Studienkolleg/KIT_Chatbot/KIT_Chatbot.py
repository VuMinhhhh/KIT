# -*- coding: utf-8 -*-
import random

Zufallsantworten = [
    "Oh, wirklich",
    "Interessant ...",
    "Das kann man so sehen",
    "Ich verstehe ...",
    "Erzähl mir mehr.",
    "Warum denkst du das?",
    "Das klingt spannend.",
    "Aha, so ist das also.",
    "Darüber habe ich noch nie nachgedacht.",
    "Meinst du wirklich?",
    "Das ist eine interessante Perspektive.",
    "Kannst du das genauer erklären?",
    "Hm, das ist bemerkenswert.",
    "Ich höre zu.",
    "Mach weiter, bitte.",
    "Das habe ich nicht erwartet.",
    "Und was ist danach passiert?",
    "Klingt logisch.",
    "So kann man es auch sehen.",
    "Gute Frage ..."]

class Knoten:
    def __init__(self, Name, Inhalt = None):
        self.Name = Name
        self.Inhalt = Inhalt or ""
        self.Wert = 0
        self.Kinder = {}
    
    def hinzufuegen(self, Kind):
        self.Kinder[Kind.Name] = Kind
        
    def Inhalt_gestalten(self, Inhalt):
        self.Inhalt = Inhalt

    def suchen(self, Benutzer_Woeter):
        if self.Name in Benutzer_Woeter:
            self.Wert += 1
            for Kind in self.Kinder.values():
                Kind.Wert = self.Wert
                Kind.suchen(Benutzer_Woeter)
        else:
           for Kind in self.Kinder.values():
                Kind.Wert = self.Wert
                Kind.suchen(Benutzer_Woeter)
                
    def finden(self):
        tmp = self
        for Kind in self.Kinder.values():
            Max_Kind = Kind.finden()
            if Max_Kind.Wert > tmp.Wert:
                tmp = Max_Kind
        return tmp
            
    def darstellen(self):
        if not self.Kinder:
           print(self.Inhalt)
        else:
            if self.Inhalt:
                print(self.Inhalt)
            print("Was wollen Sie über ", self.Name, " wissen?")
            Nummer = 1
            for Kind in self.Kinder.values():
                print (Nummer, ". ", Kind.Name)
                Nummer += 1
                
    def delete(self):
        self.Wert = 0
        for Kind in self.Kinder.values():
           Kind.delete()

Chatbot = Knoten("Chatbot")
    
Kurse = Knoten("Kurse")
Chatbot.hinzufuegen(Kurse)
Studienbegleitende_Deutschkurse = Knoten("Studienbegleitende Deutschkurse")
Kurse.hinzufuegen(Studienbegleitende_Deutschkurse)
Studienbegleitende_Deutschkurse.Inhalt = """
Studienbegleitende Deutschkurse

Anmeldezeitraum Sommersemester 2025:
15.04.2025 (06:00 Uhr) - 16.04.2025 (13:00 Uhr) MEZ
Kurszeitraum SoSe 2025: 28.04.2025 - 02.08.2025

Anmeldeablauf:
Einstufung: Online-Einstufung ab A1.2 oder entsprechendes Zertifikat vom Studienkolleg (KIT)
Den Link zur Online-Einstufung finden Sie jeweils bei der Kursbeschreibung in der Kursübersicht. Bitte eventuell Cookies erlauben und Link nochmals im Browser bestätigen.
Online-Anmeldung über Campus+ 

Kosten:
Internationale Studierende des KIT sind für einen Deutschkurs pro Semester von der Gebührenpflicht befreit.
Die reguläre Kursgebühr finden Sie jeweils bei den Kursdaten unter Kursübersicht.

Es gelten die Ferien und Feiertage der regulären Vorlesungszeit
"""
Teilnahmebedingungen = Knoten("Teilnahmebedingungen")
Studienbegleitende_Deutschkurse.hinzufuegen(Teilnahmebedingungen)
teilnehmen = Knoten("teilnehmen")
Teilnahmebedingungen.hinzufuegen(teilnehmen)
teilnehmen.Inhalt = """
Teilnehmen können:
- Studierende, Doktorandinnen/Doktoranden und Postdocs am KIT
- Mitarbeiterinnen/Mitarbeiter des KIT
- Partner von KIT-Mitarbeiterinnen/Mitarbeitern
"""
anmelden = Knoten("anmelden")
Teilnahmebedingungen.hinzufuegen(anmelden)
anmelden.Inhalt = """
Vor der Anmeldung ist eine Einstufung vorzunehmen. Die Anmeldung erfolgt ausschließlich online über Campus+. 
Verspätete Anmeldungen können nicht berücksichtigt werden.
"""
Einstufung = Knoten("Einstufung")
Teilnahmebedingungen.hinzufuegen(Einstufung)
Einstufung.Inhalt = """
Für den Besuch eines Kurses ist ein Einstufungstest obligatorisch. Einstufungstests entscheiden über die endgültige Teilnahme.
Sollte der Test versäumt werden, übernimmt das Studienkolleg keine Gewähr dafür, dass man den gebuchten Kurs tatsächlich besuchen kann und dieser dem gewünschten Sprachniveau entspricht.
"""
Gebühren = Knoten("Gebühren")
Teilnahmebedingungen.hinzufuegen(Gebühren)
Gebühren.Inhalt = """
Das KIT erhebt für die Teilnahme an studienbegleitenden Deutschkursen Gebühren. Den Betrag entnehmen Sie bitte der Kursbeschreibung.
Ausländische immatrikulierte Studierende des KIT sind für einen Deutschkurs pro Semester von der Gebührenpflicht befreit.
Die Anmeldung ist verbindlich und die Kursgebühr ist vor Beginn des jeweiligen Deutschkurses fällig.
Der/Die Angemeldete akzeptiert mit der verbindlichen Anmeldung die allgemeinen Regeln des Studienkollegs.
 
Gebührenerstattung
Bei einem Rücktritt vor Beginn des jeweiligen Deutschkurses wird eine bereits bezahlte Kursgebühr erstattet, wenn eine schriftliche Rücktrittserklärung bis maximal 10 Tage vor Beginn des jeweiligen Kurses im Sekretariat des Studienkollegs eingegangen ist.
"""
Warteliste = Knoten("Warteliste")
Teilnahmebedingungen.hinzufuegen(Warteliste)
Warteliste.Inhalt = """
Wenn ein Platz frei wird und Teilnehmende auf der Warteliste nachrücken können, erhalten sie eine Benachrichtigung per E-Mail. 
"""
Kurswechsel = Knoten("Kurswechsel")
Teilnahmebedingungen.hinzufuegen(Kurswechsel)
Kurswechsel.Inhalt = """
Sie melden sich nur für den Kurs an, der Ihrem Sprachniveau entspricht und für den Sie Zeit haben. Sie haben keinen Anspruch auf Kurswechsel: Ein nachträglicher Kurswechsel ist also nicht möglich.
"""
Leistungsnachweise = Knoten("Leistungsnachweise")
Teilnahmebedingungen.hinzufuegen(Leistungsnachweise)
Leistungsnachweise.Inhalt = """
ECTS-Punkte werden vergeben bei:
- aktiver Mitarbeit
- erfolgreicher Klausur am Ende des Kurses.

Eine mögliche Einsicht Ihrer Klausurantworten können Sie bis spätestens 4 Wochen nach Mitteilung des Ergebnisses schriftlich beantragen
"""
Mindestteilnehmerzahl = Knoten("Mindestteilnehmerzahl")
Teilnahmebedingungen.hinzufuegen(Mindestteilnehmerzahl)
Mindestteilnehmerzahl.Inhalt = """
Die Mindestteilnehmerzahl beträgt 15 Personen. Bei einer geringeren Anzahl von Anmeldungen kann der Kurs abgesagt werden.
"""
Anmeldung = Knoten("Anmeldung")
Studienbegleitende_Deutschkurse.hinzufuegen(Anmeldung)
Anmeldung.Inhalt = """
Anmeldezeiten zu den studienbegleitenden Deutschkursen des Studienkollegs

Zwischen dem 17.10.2024, 06:00 Uhr und dem 18.10.2024, 13:00 Uhr (CET) hatten KIT-Studierende, KIT-Mitarbeiter/innen und deren Ehegatten die Möglichkeit, sich für die studienbegleitenden Deutschkurse des Studienkollegs anzumelden.
Am 23.10.2024 von 10:00 Uhr bis 12:00 Uhr (CET) wurde die Anmeldung zu den studienbegleitenden Deutschkursen des Studienkollegs auch für Studierende fremder Hochschulen geöffnet, sofern Restplätze vorhanden!
Falls Sie sich bereits für einen Kurs angemeldet haben, können Sie die Daten überprüfen:
"""
TestDaF_Vorbereitung = Knoten("TestDaF-Vorbereitung")
Kurse.hinzufuegen(TestDaF_Vorbereitung)
TestDaF_Vorbereitung.Inhalt = """
Das Studienkolleg bietet als lizenziertes Prüfungszentrum für TestDaF verschiedene TestDaF-Kurse für jeweils unterschiedliche Zielgruppen an:
D-Kurse
Kompaktkurs
Basiskurs

Zielgruppe: Studienbewerber*in mit einer gültigen Vormerkung des KIT, die einen Sprachnachweis zur Aufnahme in ein Fachstudium erbringen müssen

Zeitraum: ein bis zwei Semester

Status: Sie werden als Student*in eingeschrieben
"""
D_Kurse = Knoten("D-Kurse")
TestDaF_Vorbereitung.hinzufuegen(D_Kurse)
D_Kurse.Inhalt = """
D-Kurse

Die studienvorbereitenden TestDaF-Kurse (D-Kurse: D120 und D220) bereiten innerhalb von ein- bis zwei Semestern auf den digitalen TestDaF vor. Anhand des Ergebnisses des Aufnahmetests werden Sie entsprechend Ihrem Sprachniveau entweder dem zweisemestrigen Deutschkurs (D120) oder dem einsemestrigen Deutschkurs (D220) zugewiesen. Die Teilnahme am Kurs ist mit der Einschreibung am Studienkolleg des KIT verbunden. Die Kurse erweitern die sprachlichen Fertigkeiten im Hinblick auf die täglichen sprachlichen Anforderungen im Studium. Zum Abschluss des Kurses garantieren wir Ihnen einen Prüfungsplatz für den digitalen TestDaF, den Sie an unserem TestDaF-Zentrum ablegen.
Ein Hochschulstudium erfordert von internationalen Studierenden verschiedene kommunikative Kompetenzen, um die sprachlichen Herausforderungen bewältigen und das Studium erfolgreich abschließen zu können. Eine Auswahl dieser studienrelevanten Kompetenzen wurde vom TestDaF-Institut ermittelt und findet sich in den Aufgaben des digitalen TestDaF wieder. In unseren studienvorbereitenden TestDaF-Kursen fördern wir die Erweiterung Ihrer sprachlichen Kompetenzen und machen Sie mit dem Testformat der vier Testteile des digitalen TestDaF (Leseverstehen, Hörverstehen, Schriftlicher Ausdruck und Mündlicher Ausdruck) vertraut. Gemeinsam erarbeiten wir Lösungsstrategien für die einzelnen Aufgabentypen und trainieren gezielt den Umgang mit den Prüfungsaufgaben. Außerdem nehmen Sie an einer kompletten TestDaF-Modellprüfung teil. Tests während des Semesters ermöglichen eine detaillierte Einschätzung Ihrer Leistung und werden durch wichtige Lerntipps für die finale Testvorbereitung ergänzt.
 
Kursinhalte
- Gespräche zur Studienorganisation und zu Alltagsfragen führen
- fachbezogene Gespräche mit Kommilitoninnen, Kommilitonen und Lehrkräften führen, dabei auch auf Lektüre Bezug nehmen
- Kurzpräsentationen halten
- auf andere Beiträge reagieren und/oder einen eigenen Beitrag leisten
- Diskussionen folgen und ggf. Stellung nehmen
- Vorlesungen / Vorträge hören, dabei Handout / PowerPoint-Folien lesen, Notizen machen
- Lektüre verarbeiten, Notizen machen, Texte dazu schreiben
- schriftliche Texte wie Forumsbeiträge oder Textpassagen für eine Hausarbeit verfassen
 
Erforderliche Ausstattung
- eigener PC oder Laptop
- zuverlässige Internetverbindung
- Browser
 
Gebühr: 550 € pro Semester

Umfang: 20 SWS (ca. 280 UE) pro Semester
"""
Aufnahmeverfahren_D = Knoten("Aufnahmeverfahren")
D_Kurse.hinzufuegen(Aufnahmeverfahren_D)
Aufnahmeverfahren_D.Inhalt = """
Aufnahmeverfahren: Studienvorbereitende Deutsch-Kurse

1. Bewerbung: Bitte bewerben Sie sich beim International Students Office (IStO) des Karlsruher Institut für Technologie (KIT) oder beim Akademischen Auslandsamt (Studierendensekretariat)  der Universität Stuttgart.
Für Fragen zum Bewerbungsverfahren wenden Sie sich bitte an das IStO bzw. das Studierendensekretariat.
2. Registrierung: Nach Erhalt einer Vormerkung werden Sie automatisch an das Studienkolleg für einen Aufnahmetest gemeldet und erhalten mit der Vormerkung eine Aufforderung zur Registrierung. Bitte registrieren Sie sich unter Registrierung AT-D im angegebenen Zeitraum für den Aufnahmetest.
3. Zulassung Aufnahmetest: Sie werden nach Ende des Registrierungszeitraumes informiert, ob Sie am Aufnahmetest teilnehmen können. Sollten mehr Registrierungen als Plätze vorliegen, wird ein Auswahlverfahren durchgeführt.
4. Aufnahmetest: Am Aufnahmetest kann nur mit einer gültigen Vormerkung teilgenommen werden. Bringen Sie daher Ihre Vormerkung zum Aufnahmetest mit. Der Aufnahmetest dauert ca. einen gesamten Vormittag.
5. Bekanntgabe: Je nach Testergebnis und Verfügbarkeit der Plätze werden Sie in das Studienkolleg aufgenommen. Die Bekanntgabe der Ergebnisse erfolgt zeitnah (ca. 3 Werktage) nach dem Aufnahmetest.
6. Einschreibung: Wenn Sie in das Studienkolleg aufgenommen werden, erfolgt zwischen Aufnahmetest und Kursbeginn die Einschreibung. Nähere Informationen hierzu erhalten Sie mit dem Aufnahmebescheid.
"""
Aufnahmetest_D = Knoten("Aufnahmetest")
D_Kurse.hinzufuegen(Aufnahmetest_D)
Aufnahmetest_D.Inhalt = """
Zweck des Aufnahmetests
Der Aufnahmetest überprüft die Deutschkenntnisse vor der Aufnahme in einen TestDaF-Vorbereitungskurs (D-Kurs). Anhand des Ergebnisses des Aufnahmetests werden Sie entsprechend Ihrem Sprachniveau entweder dem zweisemestrigen Deutschkurs (D120) oder dem einsemestrigen Deutschkurs (D220) zugewiesen
Nur mit einer gültigen Vormerkung und einer Platzzusage nach erfolgreicher Registrierung können Sie am Aufnahmetest teilnehmen.

Umfang und Inhalt des Aufnahmetests
Dieser Aufnahmetest ist obligatorisch für alle, die in einen TestDaF-Vorbereitungskurs (D-Kurs) des Studienkollegs aufgenommen werden wollen. Er wird elektronisch mit Hilfe des Systems onSET (siehe unten) durchgeführt. Dauer (reine Arbeitszeit): ca. 40 Minuten.

Form des Aufnahmetests: C-Test
Der Aufnahmetest findet mit Hilfe des onSET statt.
Sie brauchen keine Computerkenntnisse. Sie müssen nur wissen, wie Tastatur und Maus zu bedienen sind. Alles andere läuft automatisch.
Der onSET enthält acht Aufgaben. Jede Aufgabe besteht aus einem Lückentext mit genau 20 Lücken. Für jeden Lückentext haben Sie maximal fünf Minuten Zeit. Insgesamt dauert der Test also maximal 40 Minuten.
Weitere Informationen finden Sie auf der Internetseite https://www.onset.de/.
Sie können sich auf onSET mit einem Beispieltest vorbereiten. Der Test ist kürzer und etwas leichter als der onSET. Aber er zeigt Ihnen, was Sie bei onSET machen müssen.

Termine
- Registrierung Aufnahmetest (nur nach vorheriger Bewerbung und Erhalt einer Vormerkung möglich): wird noch bekanntgegeben
- Termin Aufnahmetest:
  + Mittwoch, 17. September 2025
  + Der Aufnahmetest findet in Präsenz in Karlsruhe statt
- Einschreibung: Die Einschreibung erfolgt online.
- Beginn Deutschkurse: Montag, 06. Oktober 2025
"""
TestDaf_Prüfung = Knoten("TestDaf-Prüfung")
D_Kurse.hinzufuegen(TestDaf_Prüfung)
TestDaf_Prüfung.Inhalt = """
Ein Angebot zur Prüfungsvorbereitung Digitaler TestDaF am KIT finden Sie 

- Nächster Prüfungstermin:	
16. April 2025

- Ort:	
TestDaF-Zentrum am Studienkolleg des KIT, SCC, Geb.  20.21, Zirkel 2, 76131 Karlsruhe

- Ziel:	
TestDaF misst die Sprachfähigkeit im akademischen Kontext

- Testtyp:	
Standardisierter weltweit eingesetzter Proficiency-Test

- Testteile:	
TestDaF besteht aus vier Testteilen:
  + Lesen 
  + Hören
  + Schreiben
  + Sprechen

- Gebühr:	
210 €

- Vorbereitung:	
zur Vorbereitung am KIT 

- Anzahl Wiederholungs-versuche:
unbeschränkt

- Regeln für TestDaF-Prüfungsteilnehmende 	
https://www.testdaf.de/de/agb/

- Anzahl Plätze:	
beschränkt

- Fit für den TestDaF?
Mit diesem kurzen Test können Sie in wenigen Minuten Ihre Deutschkenntnisse überprüfen.

- Prüfungsbeauftragte:	
Adisa Kadic
 
Hinweise:
Am KIT wird ein in allen 4 Subtests mit der TestNiveaustufe 4 oder höher bewerteter TestDaF als Alternative zur DSH anerkannt.
Testerstellung und Korrektur erfolgen zentral im TestDaF-Institut in Hagen.
Detaillierte Informationen zu TestDaF entnehmen Sie bitte der Homepage des TestDaF-Instituts .
"""
Kompaktkurs = Knoten("Kompaktkurs")
TestDaF_Vorbereitung.hinzufuegen(Kompaktkurs)
Kompaktkurs.Inhalt = """
Kompaktkurs Prüfungsvorbereitung für den digitalen TestDaF

Zielgruppe: alle:
- die das Niveau B2/C1 erreicht haben und
- demnächst an einem digitalen TestDaF teilnehmen wollen.

Der Kompaktkurs bereitet in 56 Unterrichtseinheiten (à 45 min) gezielt auf den digitalen TestDaF vor. Ziel ist es, den digitalen TestDaF kennenzulernen und sich in kurzer Zeit auf die Aufgabentypen und die Durchführung am Computer vorzubereiten.

Nach einer Einführung in den Prüfungsablauf sowie die besonderen Bedingungen der Prüfung werden die verschiedenen Subtests (Lesen, Hören, Schreiben, Sprechen) und ihre speziellen Schwierigkeiten anhand von Beispielen vorgestellt. Die Teilnehmer entwickeln Strategien zur Bearbeitung der Aufgaben und wenden ihr neues Wissen in Beispielaufgaben an. Für eine Teilnahme am Kurs ist folgende Ausstattung erforderlich:
- eigener PC oder Laptop
- zuverlässige Internetverbindung
- Browser.

- Kursbeginn:	15. August 2025
- Kursende:	12. September 2025
- Unterrichtszeiten:	
  + vormittags: montags, mittwochs und freitags jeweils 4 UE von 9.00 bis 12.15 Uhr 
  + In der letzten Kurswoche vom 08.09 - 12.09. wird es auch einen Kurstermin am Do., den 11.09. geben.
- Vorkenntnisse (müssen nicht nachgewiesen werden):	B2/C1
- Anmeldung nur online:	über Campus+ https://plus.campus.kit.edu/signmeup/procedures/4853
- Lehrwerk:	
  + Mit Erfolg zum digitalen TestDaF. Kurs- und Übungsbuch. Klett Verlag
  + ISBN 978-3-12-676827-6
- Kursgebühr:	
  + 200 EUR 
  + Ausländische Studierende des KIT sind für einen Deutschkurs pro Semester von der Gebührenpflicht befreit (z.B. Kompaktkurs Prüfungsvorbereitungskurs Digitaler TestDaF)
- Probedurchlauf digitaler TestDaF: geplant
- Platzreservierung für die nächste Prüfung:	garantiert (18.09.2025)
- Unterrichtsformat: Präsenzunterricht
- Nächster Prüfungstermin digitaler TestDaF (Kompaktkurs):	18.09.2025
- Anmeldefrist für die Prüfung: 08.09.2025
- Prüfungsgebühr: 210 EUR
- Nächster Kompaktkurs 2026: Frühjahr 2026

Wichtige Informationen
Mit der Bezahlung der Kursgebühr ist die Anmeldung für beide Seiten verbindlich.
Der/Die Angemeldete akzeptiert mit der verbindlichen Anmeldung die allgemeinen Regeln des Studienkollegs.
Eine Erstattung der Gebühren erfolgt
- bei einer Abmeldung bis 10 Tage vor Kursbeginn
- wenn ein Kurs nicht stattfinden kann.
"""
Basiskurs = Knoten("Basiskurs")
TestDaF_Vorbereitung.hinzufuegen(Basiskurs)
Basiskurs.Inhalt = """
Basiskurs digitaler TestDaF

- Zielgruppe: KIT-Mitglieder:
  + die noch nicht das Niveau B2 erreicht haben oder nur im unteren Bereich des Niveaus angelangt sind und
  + einige Aufgaben des digitalen TestDaF kennenlernen wollen.
- Zeitraum: analog zu Vorlesungszeiten am KIT
- Umfang: 10 SWS
- ECTS: bei erfolgreicher Abschlussklausur und regelmäßiger Teilnahme werden 10 ECTS vergeben (kein TestDaF-Zertifikat!).

Der Basiskurs ist ein semesterbegleitender Deutschkurs, der in 10 Stunden pro Woche zum Niveau B2.1/B2.2 des GER führt. Ziel ist es vor allem, Kompetenzen zur Bewältigung von kommunikativen Aufgaben zu erweitern. Darüber hinaus wird an die verschiedenen Aufgabentypen des digitalen TestDaF herangeführt. Nach erfolgreichem Abschluss des Basiskurses hat man die Möglichkeit, in kurzer Zeit und im Rahmen eines Prüfungsvorbereitungskurses / Kompaktkurses am KIT Strategien zur Bearbeitung der Prüfungsaufgaben zu entwickeln (Formattraining) und sich somit auf die Prüfung vorzubereiten.

Für eine Teilnahme am Kurs ist folgende Ausstattung erforderlich:
- Lehrwerk
- eigner Laptop (erwünscht).

Wichtige Informationen
Mit der Bezahlung der Kursgebühr ist die Anmeldung für beide Seiten verbindlich.
Der/Die Angemeldete akzeptiert mit der verbindlichen Anmeldung die allgemeinen Regeln des Studienkollegs.
Eine Erstattung der Gebühren erfolgt
- bei einer Abmeldung bis 10 Tage vor Kursbeginn
- wenn ein Kurs nicht stattfinden kann.
"""
T_Kurse = Knoten("T-Kurse")
Kurse.hinzufuegen(T_Kurse)
T_Kurse.Inhalt = """
T-Kurse am Studienkolleg Karlsruhe

Der T-Kurs bereitet Studienbewerber auf ein naturwissenschaftlich-technisches Studium an einer Universität vor. Nach zwei Semestern wird eine Feststellungsprüfung (FSP) geschrieben, die - sofern erfolgreich bestanden - zu einem Studium eines T-Faches deutschlandweit berechtigt.
Die Kurse werden durch mehrere Fachdozenten betreut und ermöglichen aufgrund der kleinen Kursgröße (ca. 25 Studierende) einen regen Austausch sowohl zwischen Studierenden und dem jeweiligen Dozenten als auch zwischen den Studierenden selbst. Unterrichtet werden die Fächer Deutsch, Mathematik, Physik und Chemie/Informatik. Ergänzend zum Unterricht findet in Mathematik und Physik wöchentlich ein Tutorium statt, im zweiten Semester wird der Unterricht zusätzlich durch ein Praktikum ergänzt. Die Inhalte orientieren sich an den Kompetenzprofilen der Fächer an den Studienkollegs.
"""
Aufnahmeverfahren_T = Knoten("Aufnahmeverfahren")
T_Kurse.hinzufuegen(Aufnahmeverfahren_T)
Aufnahmeverfahren_T.Inhalt = """
Aufnahmeverfahren: T- Kurs

1. Bewerbung: Bitte bewerben Sie sich beim International Students Office (IStO) des Karlsruher Institut für Technologie (KIT) oder beim Akademischen Auslandsamt (Studierendensekretariat)  der Universität Stuttgart.
Für Fragen zum Bewerbungsverfahren wenden Sie sich bitte an das IStO bzw. das Studierendensekretariat.
Deutsche Staatsangehörige, die über einen ausländischen Vorbildungsnachweis verfügen und ihr Studium an einer baden-württembergischen Universität aufnehmen wollen, müssen sich zwecks Anerkennung des ausländischen Vorbildungsnachweises und der Feststellung einer Äquivalenz zur deutschen allgemeinen oder fachgebundenen Hochschulreife an das Regierungspräsidium Stuttgart des Landes Baden-Württemberg wenden.
Bitte beachten: Die Anerkennung des ausländischen Vorbildungsnachweises muss beim Regierungspräsidium Stuttgart des Landes Baden-Württemberg gestellt werden und bei der Universität nachgereicht werden. Deutsche Staatsangehörige mit ausländischem Vorbildungsnachweis, die ein Studium in einem Studienfach an einer baden-württembergischen Hochschule aufnehmen möchten, das nicht einer bundesweiten Zulassungsbeschränkung unterliegt, müssen einen schriftlichen Antrag auf Prüfung und Feststellung der im Ausland erworbenen Hochschulzugangsberechtigung beim Regierungspräsidium Stuttgart des Landes Baden-Württemberg stellen.

2. Registrierung: Nach Erhalt einer Vormerkung werden Sie automatisch an das Studienkolleg für einen Aufnahmetest gemeldet und erhalten mit der Vormerkung eine Aufforderung zur Registrierung. Bitte registrieren Sie sich unter Registrierung AT-T im angegebenen Zeitraum für den Aufnahmetest.

3. Zulassung Aufnahmetest: Sie werden nach Ende des Registrierungszeitraumes informiert, ob Sie am Aufnahmetest teilnehmen können. Sollten mehr Registrierung als Plätze vorliegen, wird ein Auswahlverfahren durchgeführt.

4. Aufnahmetest: Am Aufnahmetest kann nur mit einer gültigen Vormerkung teilgenommen werden. Bringen Sie daher Ihre Vormerkung zum Aufnahmetest mit. Der Aufnahmetest dauert ca. einen gesamten Vormittag und beinhaltet einen Mathematik-Test und einen Deutsch-Test.

5. Bekanntgabe: Je nach Testergebnis und Verfügbarkeit der Plätze werden Sie in das Studienkolleg aufgenommen. Die Bekanntgabe der Ergebnisse erfolgt zeitnah (ca. 5 Werktage) nach dem Aufnahmetest.

6. Einschreibung: Wenn Sie in das Studienkolleg aufgenommen werden, erfolgt zwischen Aufnahmetest und Kursbeginn die Einschreibung. Nähere Informationen hierzu erhalten Sie mit dem Aufnahmebescheid.
"""
Aufnahmetest_T = Knoten("Aufnahmetest")
T_Kurse.hinzufuegen(Aufnahmetest_T)
Aufnahmetest_T.Inhalt = """
Aufnahmetest T-Kurs (AT-T)

Der Aufnahmetest für T-Kurs-Bewerber besteht aus zwei Teilen:
- ca. einstündige Online-Prüfung im Fach Mathematik
- ca. einstündige Online-Prüfung im Fach Deutsch
Beide Prüfungsteile werden am gleichen Tag abgelegt.
 
Bitte beachten Sie:
- Nur mit einer gültigen Vormerkung und einer Platzzusage nach erfolgreicher Registrierung (s.u.) können Sie am Aufnahmetest teilnehmen.
- Es sind keine Hilfsmittel zugelassen.
- Bitte bringen Sie zum Aufnahmetest Ihre Vormerkung und Ihren Reisepass mit.
- Bitte bringen Sie Ihre Zugangsdaten zu folgenden Portalen mit: onSET, Ilias
- Bitte kommen Sie pünktlich
 
Informationen zur Deutschprüfung
Der Aufnahmetest findet mit Hilfe des onSET (= Online-Einstufungstest) statt. Sie brauchen keine Computerkenntnisse. Sie müssen nur wissen, wie Tastatur und Maus zu bedienen sind. Alles andere läuft automatisch. Der onSET enthält acht Aufgaben. Jede Aufgabe besteht aus einem Lückentext mit genau 20 Lücken. Für jeden Lückentext haben Sie maximal fünf Minuten Zeit. Insgesamt dauert der Test also maximal 40 Minuten.
Weitere Informationen finden Sie auf der Internetseite https://www.onset.de/. Sie können sich auf onSET mit einem Beispieltest vorbereiten. Der Test ist kürzer und etwas leicher als der onSET. Aber er zeigt Ihnen, was Sie bei onSET machen müssen.

Informationen zur Mathematikprüfung
- Format: Der Aufnahmetest Mathematik findet als elektronische Prüfung im Ilias des KIT statt.
- Inhalt:	Lehrstoff einschließlich der 11. Klasse eines deutschen Gymnasiums
- Vorbereitung: https://www.schlaukopf.de
Hinweis: Integralrechnung ist kein Bestandteil des Aufnahmetests mehr.
Beispielaufgaben: Um die Beispielaufgaben bearbeiten zu können, benötigen Sie zuerst einen Ilias-Account! Sie müssen am Ilias angemeldet sein, um die Aufgaben bearbeiten zu können.

Termine
- Registrierung Aufnahmetest (nur nach vorheriger Bewerbung und Erhalt einer Vormerkung möglich): Zeitraum wird noch bekanntgegeben
- Termin Aufnahmetest:
  + Dienstag, 02. September 2025
  + Der Aufnahmetest findet  i n  P r ä s e n z  in Karlsruhe statt.
- Einschreibung: Erfolgt online
- Vorlesungsbeginn T1-Kurse: Montag, 06. Oktober 2025
"""
Feststellungsprüfung = Knoten("Feststellungsprüfung")
T_Kurse.hinzufuegen(Feststellungsprüfung)
Feststellungsprüfung.Inhalt = """
Feststellungsprüfung im Sommersemester 2025
 
1. Termine schriftlich:  
- Fach Deutsch am Samstag, den 21.06.2025, im Gottlieb-Daimler-Hörsaal und Carl-Benz-Hörsaal 
  + im Gebäude 10.21
  + Prüfungszeit: 09:00 - 14:00 Uhr
  + Kurse T2a, T2b, T2c, T2d sowie externe und vorgezogene FSP
 
- Fach Mathematik am Montag, den 23.06.2025, im Gebäude 50.20
  + Prüfungszeit: 08:30 - 11:30 Uhr
  + Kurs T2a im Raum N.N.
  + Kurs T2b im Raum N.N.
  + Kurs T2c im Raum N.N.
  + Kurs T2d im Raum N.N.
  + vorgezogene und externe FSP im Raum N.N.
 
- Fach Physik oder Chemie oder Informatik am Dienstag, den 24.06.2025, im Gebäude 50.20
  + Prüfungszeit: 08:30 - 11:30 Uhr
  + Kurs T2a im Raum N.N.
  + Kurs T2b im Raum N.N.
  + Kurs T2c im Raum N.N.
  + Kurs T2d im Raum N.N.
  + vorgezogene und externe FSP im Raum N.N.

2. Termine mündlich:  
Die Liste der mündlichen Prüfungen (01.07. - 03.07.2025) wird am Montag, den 30.06.2025, veröffentlicht.

3. Prüfungsergebnis: 
Die Prüfungsergebnisse können ab Freitag, den 04.07.2025, im STKportal abgerufen werden.

4. Nachprüfung: 	
Nachprüfungen sowie deren Ort und Termin werden im STKportal mit den Prüfungsergebnissen bekannt gegeben.

5. Nachprüfungsergebnis:	
Die Ergebnisse der Nachprüfungen können ab Donnerstag, den 10.07.2025, im STKportal abgerufen werden.

6. Prüfungseinsicht:  
Die Einsicht in die schriftlichen Teile der FSP ist NUR am 07.07.2025, von 10:00 bis 11:00 Uhr in den Räumen 204, 207 und 256 im Gebäude 50.20 möglich.

7. Prüfungsumfang:  
- Die Feststellungsprüfung gliedert sich in einen schriftlichen und einen mündlichen Teil.
- Gegenstand der schriftlichen Prüfung sind drei Fächer:
  + Deutsch (ca. 4,5 Stunden inkl. kurzer Pausen)
  + Mathematik (180 Minuten)
  + Physik oder Chemie oder Informatik (180 Minuten)
- Die mündliche Prüfung umfasst die Fächer der schriftlichen Prüfung und ein weiteres
- in der schriftlichen Prüfung nicht gewähltes Fach (Chemie oder Informatik oder Physik).
"""
Prüfungsverfahren = Knoten("Prüfungsverfahren")
Feststellungsprüfung.hinzufuegen(Prüfungsverfahren)
Prüfungsverfahren.Inhalt = """
1. Interne Teilnehmer (T-Kurs Teilnehmer )
- Interne Teilnehmer, d.h. Studierende, die das Studienkolleg besuchen, erhalten in allen Fächern (Deutsch, Mathematik, Physik, Informatik mit Elektrotechnik – Praktikum oder Chemie mit Chemie - Praktikum) eine Semesternote für die Leistungen im zweiten Semester.
- Die schriftliche Prüfung erstreckt sich auf Deutsch, Mathematik, Physik oder Chemie oder Informatik. Auf die mündliche Prüfung im jeweiligen Fach kann verzichtet werden, wenn sich als Durchschnitt aus Semesternote und schriftlicher Prüfung mindestens die Note 4,0 ergibt und die Abweichung ('Betrag der Differenz') höchstens eine Note beträgt. Sind in mindestens zwei Fächern beide Noten (Semesternote und schriftliche Prüfung) nicht ausreichend, so gibt es keine mündliche Prüfung und die FSP ist nicht bestanden.
- Vorgezogene Feststellungsprüfung: Bei bestimmten Voraussetzungen kann die FSP in einem Fach (oder mehreren Fächern) vorgezogen, d.h. bereits nach dem ersten Semester abgelegt werden. Dazu muss die Durchschnittsnote des ersten Semesters in dem betreffenden Fach mindestens 2,5 sein und alle beteiligten Fachdozent(inn)en müssen zustimmen.
- Die Gesamtnote ergibt sich bei bestandener FSP aus dem (evtl. gewichteten) Durchschnitt aller relevanten Teilnoten. Von einem Fach wird man befreit, wenn die vorgezogene Prüfung bestanden bzw. (bei Wiederholung) die erbrachte Note anerkannt wurde. In diesem Fall geht die im vorgezogenen Teil erhaltene Note in die Gesamtnote ein.
- Erhält man in nur einem Fach keine ausreichende Endnote, darf man eine Nachprüfung ablegen. Die Nachprüfung findet etwa einen Monat nach der FSP statt. Die Bedingungen sind dieselben wie bei der (ersten) FP. Insbesondere bleibt die Semesternote erhalten, falls vorhanden.
- Berechnungsbeispiele: Einige Fälle, die häufig vorkommen, sind in einer Tabelle dargestellt. Folgen Sie bitte dem Link.
- Wenn Sie weitere Fragen haben, helfen wir Ihnen gerne: Mail schicken.
2. Externe Teilnehmer
"""
Prüfungsablauf = Knoten("Prüfungsablauf")
Feststellungsprüfung.hinzufuegen(Prüfungsablauf)
Deutsch = Knoten("Deutsch")
Prüfungsablauf.hinzufuegen(Deutsch)
Deutsch.Inhalt = """
Deutsch

Die schriftliche Prüfung im Fach Deutsch besteht aus einem schriftlichen und einem mündlichen Teil. Der schriftliche Teil umfasst Textproduktion, Hörverstehen und Leseverstehen sowie wissenschaftssprachliche Strukturen (Grammatik).

Wie sieht der allgemeine Ablauf der schriftlichen Prüfung aus?
- Nachdem allen Kandidatinnen / Kandidaten Plätze zugewiesen wurden, beginnt die Prüfung:
  + Textproduktion (70 Minuten Bearbeitungszeit)
  + Kurze Pause durch Einsammeln der Aufgabenblätter zur Textproduktion
  + Hörverstehen: Erstes Vorlesen des Textes
  + Austeilen der Aufgabenblätter und anschl. 10 Minuten Zeit zum Lesen der Aufgaben, Ergänzen der ersten Notizen und eventuell schon Bearbeiten der Aufgaben.
  + Zweites Vorlesen
  + 40 Minuten Zeit zum Bearbeiten der Aufgaben
  + 15 Minuten Pause
  + Leseverstehen und wissenschaftssprachliche Strukturen (90 MinutenBearbeitungszeit)
- Gesamtdauer des Prüfungsteils Deutsch: ca. 4,5 Stunden (inkl. Pausen und Vortragszeit)

Was bedeutet Textproduktion?
- Bei diesem Prüfungsteil soll nachgewiesen werden, dass man in der Lage ist, sich mit Hilfe eines Schaubilds, einer Grafik (Statistik) und/oder ein bis zwei Statements selbstständig und zusammenhängend zu einem studienbezogenen und wissenschaftsorientierten Thema schriftlich zu äußern und einen argumentativen Sachtext zu verfassen. Der Umfang soll nicht mehr als 250 Wörter betragen.
- Bearbeitungszeit: 70 Minuten

Was bedeutet Hörverstehen?
- Das Ziel dieses Teils ist die Bearbeitung von Aufgaben zu einem Sachtext mit wissenschaftlichem Hintergrund. Die Beantwortung erfolgt auf der Basis einer Mitschrift bei enger Anlehnung an den zweimal vorgelesenen Text. Es dürfen bereits beim ersten Vortrag Notizen gemacht werden. Sie erhalten die Aufgaben erst nach dem ersten Vortrag.
- Bearbeitungszeit nach dem ersten Vortrag: 10 Minuten
- Bearbeitungszeit nach dem zweiten Vortrag: 40 Minuten

Was bedeutet Leseverstehen und wissenschaftssprachliche Strukturen (Grammatik)?
- Leseverstehen und wissenschaftssprachliche Strukturen (Grammatik) gliedert sich insgesamt in zwei Teilprüfungen.
- Beim ersten Prüfungsteil sind Aufgaben zu einem vorgelegten Text zu bearbeiten und Fragen zu Sprache und Inhalt zu beantworten. Die erforderliche Kompetenz im Leseverstehen wird beispielsweise nachgewiesen durch: 
  + die Bildung von Überschriften zu einzelnen Abschnitten
  + die Erklärung schwieriger Ausdrücke, Satzteile oder Sätze
  + die Erläuterung der unterschiedlichen Bedeutung artverwandter Wörter
  + Beantwortung von Fragen
  + Darstellung der Argumentationsstruktur des Textes.
- Beim zweiten Prüfungsteil werden Aufgaben gestellt, durch deren Bearbeitung die Kandidatinnen und Kandidaten ihre Fähigkeit im Umgang mit wissenschaftssprachlich relevanten grammatischen Strukturen nachweisen sollen. Dabei handelt es sich in der Regel um Umformungen. Grundlage für die Umformungen ist ein Satz (oder mehrere zusammenhängende Sätze) aus dem Lesetext. Schwerpunkte sind:
  + Passiv
  + Modalverben
  + Verbalisierungen und Nominalisierungen
  + Partizipialkonstruktionen und Relativsätze
  + Adverbialsätze
  + Konjunktiv I und II
- Bearbeitungszeit: 90 Minuten

Was bedeutet mündliche Prüfung?
- Wenn Sie nach den Vorgaben der Prüfungsordnung zur Feststellungsprüfung an einer mündlichen Prüfung teilnehmen müssen, erhalten Sie einen individuellen Termin innerhalb des mündlichen Prüfungszeitraums.
- Ablauf: Sie haben zunächst 20 Minuten Zeit, sich anhand einer Gesprächsvorlage (Grafik, Schaubild oder kurzer Text) auf die mündliche Prüfung vorzubereiten. Im Anschluss daran findet die eigentliche Prüfung über das Thema der Gesprächsvorlage statt. Die Dauer der Prüfung beträgt 15 bis 20 Minuten.

Studienbewerber/innen können auf Antrag von der Prüfung im Fach Deutsch befreit werden, wenn sie eines der folgenden Zeugnisse vorlegen können:
  + das Deutsche Sprachdiplom der Kultusministerkonferenz – Zweite Stufe,
  + das Zeugnis über das bestandene Goethe-Zertifikat C2 (Großes Deutsches Sprachdiplom (GDS) des Goethe-Instituts),
  + das Zeugnis über die Deutsche Sprachprüfung für den Hochschulzugang (DSH) mit dem Gesamtergebnis mindestens DSH-2,
  + den Test Deutsch als Fremdsprache für ausländische Studienbewerber/innen (TestDaF) mit einem Ergebnis, das in allen vier Teilprüfungen mindestens die TestDaF-Niveaustufe 4 ausweist,
  + das Zeugnis über die bestandene Prüfung „telc Deutsch C1 Hochschule“
Der Prüfungsteil Deutsch kann zu einem früheren Termin abgelegt werden (Vorgezogene Deutschprüfung).
"""
Mathematik = Knoten("Mathematik")
Prüfungsablauf.hinzufuegen(Mathematik)
Mathematik.Inhalt = """
- Die Bearbeitungszeit der schriftlichen Prüfung beträgt 3 Stunden (180 Minuten). Als Hilfsmittel sind zugelassen ein (nichtprogrammierbarer) Taschenrechner und die Formelsammlung: Sieber; Mathematische Formeln E; Klett Verlag Stuttgart. Dem Inhalt liegt das Kompetenzprofil zugrunde.
- Die mündliche Prüfung dauert in der Regel 15 Minuten und hat den gleichen oben genannten Inhalt.
"""
Physik = Knoten("Physik")
Prüfungsablauf.hinzufuegen(Physik)
Physik.Inhalt = """
- Die Bearbeitungszeit der schriftlichen Prüfung beträgt 3 Stunden (180 Minuten). Als Hilfsmittel ist ein (nichtprogrammierbarer) Taschenrechner zugelassen. Dem Inhalt liegt das Kompetenzprofil zugrunde.
- Die mündliche Prüfung dauert in der Regel 15 Minuten und hat den gleichen oben genannten Inhalt.
"""
Informatik = Knoten("Informatik")
Prüfungsablauf.hinzufuegen(Informatik)
Informatik.Inhalt = """
- Die Bearbeitungszeit der schriftlichen Prüfung beträgt 3 Stunden (180 Minuten). Als Hilfsmittel ist zugelassen ein (nichtprogrammierbarer) Taschenrechner. Dem Inhalt liegt das Kompetenzprofil zugrunde.
- Die mündliche Prüfung dauert in der Regel 15 Minuten und hat den gleichen oben genannten Inhalt.
"""
Chemie = Knoten("Chemie")
Prüfungsablauf.hinzufuegen(Chemie)
Chemie.Inhalt = """
- Die Bearbeitungszeit der schriftlichen Prüfung beträgt 3 Stunden (180 Minuten). Als Hilfsmittel ist zugelassen ein (nichtprogrammierbarer) Taschenrechner. Dem Inhalt liegt das Kompetenzprofil zugrunde.
- Die mündliche Prüfung dauert in der Regel 15 Minuten und hat den gleichen oben genannten Inhalt.
"""
Prüfungen = Knoten("Prüfungen")
Chatbot.hinzufuegen(Prüfungen)
TestDaF = TestDaf_Prüfung
Prüfungen.hinzufuegen(TestDaF)
FSP = Feststellungsprüfung
Prüfungen.hinzufuegen(FSP)
Eignungsprüfung = Knoten("Eignungsprüfung")
Eignungsprüfung.Inhalt = """
Eignungsprüfung
 
Für die Zulassung zum Studium an einer Universität ist die Hochschulzugangsberechtigung in Form von z.B. Abitur oder Fachhochschulreife in der Regel eine Grundvoraussetzung. Um auch Berufstätigen ohne Hochschulzugangsberechtigung den Zugang zu ermöglichen, hat das Ministerium für Wissenschaft Forschung und Kunst (MWK) die Verordnung über den Zugang beruflich Qualifizierter zu einem Studium (BerufsHZVO) erlassen. Dadurch können nun Personen, welche die unter "Voraussetzungen und Zulassung" genannten Voraussetzungen erfüllen, bei der Hochschule einen Antrag auf Feststellung der fachlichen Entsprechung ihrer beruflichen Aus- und Fortbildung mit dem gewählten Studiengang stellen.
Ansprechpartner Prüfung: Priv.-Doz. Dr. Marco Busch
 
Wer darf die Prüfung am Studienkolleg des KIT ablegen?
- Am Studienkolleg des KIT werden nur Bewerber für naturwissenschaftliche und technische (ingenieurwissenschaftliche) Studienrichtungen geprüft. Bewerber an allen Hochschulen in Baden Württemberg können sich hier prüfen lassen.
- Bewerber für andere Studienrichtungen können sich am ISZ Heidelberg anmelden.
- Die Teilnahme an der Prüfung muss jährlich bis zum 31. Januar bei der zuständigen Hochschule für das folgende Wintersemester schriftlich beantragt werden.

Voraussetzungen und Zulassung
- Das Studienkolleg am KIT führt jährlich im Mai die Eignungsprüfung für den Zugang beruflich Qualifizierte zum Hochschulstudium durch.
- Die Bewerber müssen zunächst mit der Universität Kontakt aufnehmen, an der sie studieren möchten. Das jeweilige Studierendensekretariat informiert die Bewerber über die Zulassungsvoraussetzungen und berät die Bewerber bei der Studienfachwahl. Die zulassende Universität entscheidet über die Teilnahme an der Eignungsprüfung für beruflich Qualifizierte und meldet die Bewerber dann zur Prüfung an.
- Erst nach einem Gespräch mit dem Fachstudienberater erfolgt beim Studienkolleg das Beratungsgespräch über die Prüfungsinhalte.
- Eine direkte Anmeldung zur Eignungsprüfung am Studienkolleg Karlsruhe ist nicht möglich. Die Anmeldung muss durch die Universität erfolgen.
- Informationen zum Verfahrensablauf finden Sie auf den Seiten des zib unter: https://www.sle.kit.edu/vorstudium/hochschulzugang-berufstaetige.php
- Die Prüfung ist kostenpflichtig. Derzeit beträgt die Prüfungsgebühr 200,- €. Dieser Betrag ist bei der Meldung zur Prüfung fällig und muss bis spätestens 31. März bezahlt werden. Die Gebühr kann bei Rücktritt oder Verhinderung nicht zurückbezahlt werden. Bankverbindung:  Deutsche Bundesbank, Filiale Karlsruhe Kontonummer: 66001508, BLZ: 66000000, Vermerk: PSP2003600031-EP+Name des Prüflings).
- Am Studienkolleg Karlsruhe bzw. am MINT-Kolleg Baden-Württemberg in Karlsruhe und Stuttgart finden  studienvorbereitende Veranstaltungen statt. Soweit diese eignungsprüfungsrelevant sind, kann nach Maßgabe der verfügbaren Plätze an diesen (kostenpflichtigen) Unterrichtsmaßnahmen teilgenommen werden.

Gliederung und Form der Eignungsprüfung
- Die Eignungsprüfung besteht aus einer schriftlichen und einer mündlichen Prüfung.
- Die schriftliche Prüfung umfasst drei Klausuren mit einer Dauer von jeweils 120 Minuten: 
  + Deutsch: Schreiben eines Aufsatzes
  + Aufsichtsarbeit im Fach Englisch: Textverständnisaufgabe und Textproduktion in englischer Sprache
    Von der Aufsichtsarbeit im Fach Englisch kann der Bewerber bei Nachweis englischer Sprachkenntnisse der Niveaustufe B2 des „Gemeinsamen Europäischen Referenzrahmes für Sprachen“ (GER) befreit werden, wenn der Nachweis nach dem Schulrecht des Landes Baden-Württemberg oder eines anderen Bundeslandes erworben wurde.
  + Fachspezifische Prüfung: Sie enthält Aufgaben, die mit dem gewählten Studienfach in Verbindung stehen.
 - Die mündliche Prüfung bildet ein Prüfungsgespräch, das ca. 30 Minuten dauert. Inhalt des Prüfungsgesprächs sind Fragen zum Berufsweg, zum angestrebten Studienziel, zu kulturellen, politischen, gesellschaftlichen und wirtschaftlichen Themen.
"""

Mitarbeiter = Knoten("Mitarbeiter")
Chatbot.hinzufuegen(Mitarbeiter)
Mitarbeiter.Inhalt = """
Leitung
- Goll, Claudia

Stellvertretende Leitung
- Bentz, Tobias

Sekretariat
- Saljic, Olja
- Wunderlich, Juliana
- Pohl, Sabine
- Pawlowski, Sonja

Dozentinnen und Dozenten
- Bell, Hans-Dieter	
- Busch, Marco
- Dege, Christopher
- Dietrich, Gabriele
- Fahrner, Annette
- Fazlic-Walter, Ksenija
- Feddersen-Wolff, Anya
- Kadic, Adisa
- Nese, Chandrasekhar
- Runstuck, Frank
- Stirner, Thomas
- Wegner, Wolfgang
- Weldi, Dietmar
- Wilms-Grabe, Walburga
"""
Goll_Claudia = Knoten("Goll")
Mitarbeiter.hinzufuegen(Goll_Claudia)
Goll_Claudia.Inhalt = """
Dr.-Ing. Claudia Goll

Direktorin des Studienkollegs, des Sprachenzentrums und des MINT-Kollegs Baden-Württemberg
Sprechstunden: nach Vereinbarung
Raum: 304 
Tel.: +49 721 608-44928
Fax: +49 721 608-44938
claudia.goll@kit.edu
Adenauerring 2 (Geb. 50.20), 76131 Karlsruhe
"""
Bentz_Tobias = Knoten("Bentz")
Mitarbeiter.hinzufuegen(Bentz_Tobias)
Bentz_Tobias.Inhalt = """
Dr. rer. nat. Tobias Bentz
Stellvertretender Leiter
Sprechstunden: 
nach Vereinbarung
Raum: 4.06
CS 20.30
Tel.: +49 721 608-41986
Fax: +49 721 608-44938
tobias.bentz@kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Saljic_Olja = Knoten("Saljic")
Mitarbeiter.hinzufuegen(Saljic_Olja)
Saljic_Olja.Inhalt = """
Olja Saljic

Sprechstunden: 
- Montag: 13:00 - 14:00 Uhr
- Mittwoch: 8:00 - 10:00 Uhr
- sowie nach Vereinbarung, Geb. 50.20, R. 306
Raum: 306
Tel.: +49 721 608-41490
Fax: +49 721 608-44938
info@stk.kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Juliana_Wunderlich = Knoten("Juliana")
Mitarbeiter.hinzufuegen(Juliana_Wunderlich)
Juliana_Wunderlich.Inhalt = """
Juliana Wunderlich

Sprechstunden: 
- Montag: 13:00 - 14:00 Uhr
- Mittwoch: 8:00 - 10:00 Uhr
- sowie nach Vereinbarung, Geb. 50.20, R. 306
Raum: R. 306
Tel.: +49 721 608-44905
info@stk.kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Sabine_Pohl = Knoten("Sabine")
Mitarbeiter.hinzufuegen(Sabine_Pohl)
Sabine_Pohl.Inhalt = """
Sabine Pohl

Sprechstunden: 
- Montag: 14:00 - 15:00 Uhr
- Mittwoch: 14:00 - 15:00 Uhr
- sowie nach Vereinbarung, Geb. 50.20, R. 007
Raum: 007
Tel.: +49 721 608-44921
Fax: +49 721 608-44938
sabine.pohl@kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Sonja_Pawlowski = Knoten("Sonja")
Mitarbeiter.hinzufuegen(Sonja_Pawlowski)
Sonja_Pawlowski.Inhalt = """
Sonja Pawlowski

Sekretariat
Sprechstunden: 
- Montag: 13:00 - 14:00 Uhr
- Mittwoch: 8:00 - 10:00 Uhr
- sowie nach Vereinbarung
Raum: 306
Tel.: +49 721 608-41993
Fax: +49 721 608-44938
info@stk.kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Bell_Hans_Dieter = Knoten("Bell")
Mitarbeiter.hinzufuegen(Bell_Hans_Dieter)
Bell_Hans_Dieter.Inhalt = """
Hans-Dieter Bell

Mathematik / Physik
Sprechstunden: 
nach Vereinbarung
Tel.: +49 721 608-44910
Fax: +49 721 608-44938
hans-dieter.Bell@kit.edu
pir.stk.kit.edu/28.php
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Busch_Marco = Knoten("Busch")
Mitarbeiter.hinzufuegen(Busch_Marco)
Busch_Marco.Inhalt = """
PD Dr. Dipl.-Phys. Marco Busch

Leiter des Fachbereichs Mathematik und Naturwissenschaften
Sprechstunden: 
Mittwoch, 14:00-15:00 Uhr, Geb. 50.20, R. 010
Raum: 010, CS 50.20
Tel.: +49 721 608-44763
Fax: +49 721 608-44938
marco.busch@kit.edu
Adenauerring 2
76131 Karlsruhe
"""
Dege_Christopher = Knoten("Dege")
Mitarbeiter.hinzufuegen(Dege_Christopher)
Dege_Christopher.Inhalt = """
Christopher Dege

Deutsch als Fremdsprache
Sprechstunden: 
Mittwoch, 14:00-16:00 Uhr, Geb. 50.20, R. 315
 Raum: 315
Tel.: +49 721 608-44944
Fax: +49 721 608-44938
christopher.dege@kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Dietrich_Gabriele = Knoten("Dietrich")
Mitarbeiter.hinzufuegen(Dietrich_Gabriele)
Dietrich_Gabriele.Inhalt = """
Gabriele Dietrich

Deutsch als Fremdsprache
Sprechstunden: 
Mittwoch, 11:30-12:30 Uhr, Geb. 50.20, R. 311
Raum: 311
Tel.: +49 721 608-44923
Fax: +49 721 608-44938
gabriele.dietrich@kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Fahrner_Annette = Knoten("Fahrner")
Mitarbeiter.hinzufuegen(Fahrner_Annette)
Fahrner_Annette.Inhalt = """
Dr. Annette Fahrner

Dozentin
Deutsch als Fremdsprache
Sprechstunden: 
Dienstag, 10:00-11:00 Uhr, Geb. 50.20, R. 006
Raum: 006
Tel.: +49 721 608-41987
annette.fahrner@kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Fazlic_Walter_Ksenija = Knoten("Fazlic")
Mitarbeiter.hinzufuegen(Fazlic_Walter_Ksenija)
Fazlic_Walter_Ksenija.Inhalt = """
Ksenija Fazlic-Walter

Fachbereichsleiterin 'Deutsch als Fremdsprache'
Sprechstunden: 
Dienstag, 10:00-11:00 Uhr & nach Vereinbarung, Geb. 50.20, R. 312
Raum: 312
Tel.: +49 721 608-44903
Fax: +49 721 608-44938
ksenija.fazlic-walter@kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""

Feddersen_Wolff_Anya = Knoten("Feddersen")
Mitarbeiter.hinzufuegen(Feddersen_Wolff_Anya)
Feddersen_Wolff_Anya.Inhalt = """
Anya Feddersen-Wolff

Deutsch als Fremdsprache
Sprechstunden: 
Montag, 13:00-14:00 Uhr, Geb. 50.20, R. 311
Raum: 311
Tel.: +49 721 608-44923
Fax: +49 721 608-44938
anya.feddersen@kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Kadic_Adisa = Knoten("Kadic")
Mitarbeiter.hinzufuegen(Kadic_Adisa)
Kadic_Adisa.Inhalt = """
Adisa Kadic

TestDaF examination officer
Fachleitung Deutsch als Fremdsprache - studienbegleitend
Sprechstunden: 
Dienstag, 11:30-12:30 Uhr & nach Vereinbarung, Geb. 50.20, R. 008
Raum: 008
CS 50.20
Tel.: +49 721 608-42261
adisa.kadic@kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Nese_Chandrasekhar = Knoten("Nese")
Mitarbeiter.hinzufuegen(Nese_Chandrasekhar)
Nese_Chandrasekhar.Inhalt = """
Dr. rer. nat. (Uni. Pune) Chandrasekhar Nese

Sprechstunden: 
Montag, 10:00-11:00 Uhr, Geb. 50.20, R. 315
Raum: 315
Tel.: +49 721 608-44944
Fax: +49 721 608 44938
chandrasekhar.nese@kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Runstuck_Frank = Knoten("Runstuck")
Mitarbeiter.hinzufuegen(Runstuck_Frank)
Runstuck_Frank.Inhalt = """
Frank Runstuck

Mathematik / Informatik / Chemie
Sprechstunden: 
Mittwoch, 11:30-12:30 Uhr & nach Vereinbarung, Geb. 50.20, R. 309
Raum: 309
Tel.: +49 721 608-44908
Fax: +49 721 608-44938
frank.runstuck@kit.edu
pir.stk.kit.edu/28.php
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Stirner_Thomas = Knoten("Stirner")
Mitarbeiter.hinzufuegen(Stirner_Thomas)
Stirner_Thomas.Inhalt = """
Thomas Stirner

Fachleiter Informatik, Chemie, EDV
Sprechstunden: 
Freitag, 13:00-14:00 Uhr und nach Vereinbarung, Geb. 50.20, R. 309
Raum: 309
Tel.: +49 721 608-44908
Fax: +49 721 608-44938
thomas.stirner@kit.edu
pir.stk.kit.edu/28.php
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""

Wegner_Wolfgang = Knoten("Wegner")
Mitarbeiter.hinzufuegen(Wegner_Wolfgang)
Wegner_Wolfgang.Inhalt = """
Dr. phil. Wolfgang Wegner

'Deutsch als Fremdsprache'
Deutsch als Fremdsprache, Prüfungsbeauftragter TestDaF
Sprechstunden: 
Mittwoch, 13:00-14:00 Uhr, Geb. 50.20, R. 317
Bitte Voranmeldung per E-Mail.
Raum: 317
Tel.: +49 721 608-44904
Fax: +49 721 608-44938
wolfgang.wegner@kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Weldi_Dietmar = Knoten("Weldi")
Mitarbeiter.hinzufuegen(Weldi_Dietmar)
Weldi_Dietmar.Inhalt = """
Dr. rer.nat. Dietmar Weldi

Physik
Sprechstunden: 
Mittwoch, 10:00-11:00 Uhr und nach Vereinbarung, Geb. 50.20, R. 309
Raum: 309
Tel.: +49 721 608-44923
Fax: +49 721 608-44938
dietmar.weldi@kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""
Wilms_Grabe_Walburga = Knoten("Wilms-Grabe")
Mitarbeiter.hinzufuegen(Wilms_Grabe_Walburga)
Wilms_Grabe_Walburga.Inhalt = """
Dr. rer. nat. Walburga Wilms-Grabe

Physik
Sprechstunden: 
Montag, 13:00-14:00 Uhr & nach Vereinbarung, Geb. 50.20, R. 006
Raum: 006
Tel.: +49 721 608-44763
Fax: +49 721 608-44938
walburga.wilms-grabe@kit.edu
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe
"""

Kontakt_und_Anfahrt = Knoten("Kontakt und Anfahrt")
Chatbot.hinzufuegen(Kontakt_und_Anfahrt)
Kontakt_und_Anfahrt.Inhalt = """
Studienkolleg
- Das Studienkolleg befindet sich im Gebäude 50.20, am Adenauerring 2 (gegenüber der Haupteinfahrt des KIT)
- Als weiteres Unterrichtsgebäude wird Gebäude 08.03, Karl-Friedrich-Str. 17 (nähe Marktplatz) genutzt

Anreise mit der Bahn
- Die nächstgelegene Straßenbahnhaltestelle zum Gebäude 50.20 ist "Karlsruher Institut für Technologie - Durlacher Tor".
- Vom Hauptbahnhof aus erreichen Sie uns direkt mit der Line 4 (Richtung Waldstadt) und der Linie S4 (Richtung Eppingen). Eine Übersicht aller Linien, Informationen zu Fahrkarten und Abfahrtszeiten finden Sie auf den Seiten des Karlsruher Verkehrsverbunds.
"""
Kontakt = Knoten("Kontakt")
Kontakt_und_Anfahrt.hinzufuegen(Kontakt)
Kontakt.Inhalt = """
Kontakt

Studienkolleg am KIT
Adenauerring 2 (Geb. 50.20)
76131 Karlsruhe

Tel.:
+49 721 608 - 44905 / 41490 / -41993 / -44921

Fax:
+49 721 608 - 44938

Email:
info@stk.kit.edu
"""
Öffnungszeiten = Knoten("Öffnungszeiten")
Kontakt_und_Anfahrt.hinzufuegen(Öffnungszeiten)
Öffnungszeiten.Inhalt = """
Sekretariat:
Montag: 13.00 Uhr bis 14.00 Uhr
Mittwoch: 8.00 Uhr bis 10.00 Uhr
sowie nach Vereinbarung
Ausgabe von Zeugnissen/Zertifikaten nur nach vorheriger Vereinbarung.
"""
Über_uns = Knoten("Über KIT")
Chatbot.hinzufuegen(Über_uns)
Über_uns.Inhalt = """
Struktur

Das Studienkolleg (STK) gliedert sich in die Aufgabenbereich (siehe auch Organigramm):
- Studienvorbereitung ausländischer Studienbewerber*innen/Erwerb der Hochschulzugangsberechtigung für naturwissenschaftlich-technische Studiengänge (T-Kurse, TestDaF-Kurse)
- Studienbegleitende Angebote im Bereich Deutsch als Fremdsprache (DaF)
 
Unter gleicher Leitung befindet sich das Sprachenzentrum (SPZ) mit dem Schwerpunkt:
- Fremdsprachenkurse für KIT-Studierende und KIT-Mitarbeiter
sowie das MINT-Kolleg Baden-Württemberg (MK) als gemeinschaftliche Einrichtung der Universität Stuttgart und dem KIT mit den Schwerpunkten:
- Studienvorbereitende Angebote für KIT-Studieninteressierte in den MINT-Fächer (MINT = Mathematik, Informatik, Naturwissenschaft, Technik)
- Studienbegleitende Unterstützungsangebote in den MINT-Studiengängen im Übergang Schule-Hochschule
"""

bye = Knoten("bye")
Chatbot.hinzufuegen(bye)
bye.Inhalt = "Einen schönen Tag wünsche ich dir. Bis zum nächsten Mal"

def Antworten(Benutzer_Woeter):
    Chatbot.suchen(Benutzer_Woeter)
    tmp = Chatbot.finden()
    if tmp == Chatbot:
        print(random.choice(Zufallsantworten))
    else:
        tmp.darstellen()
    Chatbot.delete()

print("Willkommen beim Chatbot")
print("Wonach möchten Sie sich gerne erkundigen")
print("Zum Beenden einfach eintippen")
print("")
Benutzer_Eingabe = ""
while(Benutzer_Eingabe != "bye"):   
    leer_Eingabe = True
    while leer_Eingabe == True:
        Benutzer_Eingabe = input("Ihre Frage/Antwort: ")
        if Benutzer_Eingabe.strip() == "":
            print ("Wie bitte?")
            leer_Eingabe = True
            print("")
        else:
            leer_Eingabe = False
    #Benutzer_Woeter = Benutzer_Eingabe.lower()
    Antworten(Benutzer_Eingabe)

    
