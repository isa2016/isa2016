INSERT INTO gost(ime,prezime,mail,password,registrovan) VALUES ('Milos','Nisic','gost@gost.com','gost','1')
INSERT INTO gost(ime,prezime,mail,password,registrovan) VALUES ('Djura','Djuric','gost2@gost2.com','gost2','1')
INSERT INTO gost(ime,prezime,mail,password,registrovan) VALUES ('Nemam','Ideju','mgvero94@gmail.com','gost3','1')
INSERT INTO gost(ime,prezime,mail,password,registrovan) VALUES ('Nemam','Pojma','gost4@gost4.com','gost4','1')
INSERT INTO restoran(naziv,opis,drzava,grad,ulica,ocena,br) VALUES('ISA', 'opis', 'Srbija', 'Novi Sad', 'Ise Bajica 6',0,0)
INSERT INTO restoran(naziv,opis,drzava,grad,ulica,ocena,br) VALUES('Bolle', 'opis', 'Srbija', 'Novi Sad', 'Mise Dimitrijevica 8',0,0)
INSERT INTO sto(naziv,rejon,vrsta,kolona) values ('1',1,1,1)
INSERT INTO sto(naziv,rejon,vrsta,kolona) values ('1',1,1,2)
INSERT INTO restorani_i_stolovi(Restoran_ID,Sto_ID) values (1,1)
INSERT INTO restorani_i_stolovi(Restoran_ID,Sto_ID) values (1,2)
INSERT INTO kuvar(ime,prezime,mail,password,velicina_odece,velicina_obuce, registrovan, restoran_id, tip_kuvara) VALUES ('Stefan','Ceranic','kuv@kuv.com','kuv','XL','br43', '1', 1, 'baked')
INSERT INTO kuvar(ime,prezime,mail,password,velicina_odece,velicina_obuce, registrovan, restoran_id, tip_kuvara) VALUES ('Stefana','Ceranica','kuv2@kuv2.com','kuv2','XL','br43', '1', 1, 'salad')
INSERT INTO konobar(ime,prezime,mail,password,velicina_odece,velicina_obuce,registrovan, restoran_id) VALUES ('Milan','Gvero','kon@kon.com','kon','L','br42','1', 1)
INSERT INTO menadzer_sistema(ime,prezime,mail,password,registrovan) VALUES ('ms','ms','ms@ms.com','ms','1')
INSERT INTO menadzer_restorana(ime,prezime,mail,password,zaposlen,registrovan, restoran_id) VALUES ('men','men','men@men.com','men',1,'1',1)
INSERT INTO menadzer_restorana(ime,prezime,mail,password,zaposlen,registrovan) VALUES ('men2','men2','men2@men2.com','men2',0,'1')
INSERT INTO sanker(ime,prezime,mail,password,velicina_odece,velicina_obuce, registrovan, restoran_id) VALUES ('Pera','Peric','san@san.com','san','XL','br46','1', 1)
INSERT INTO restorani_i_menadzeri(Restoran_ID,Menadzer_restorana_ID) VALUES (1,1)
INSERT INTO restorani_i_kuvari(Restoran_ID,Kuvar_ID) VALUES (1,1)
INSERT INTO restorani_i_konobari(Restoran_ID,Konobar_ID) VALUES (1,1)
INSERT INTO restorani_i_sankeri(Restoran_ID,Sanker_ID) VALUES (1,1)
INSERT INTO jelo(naziv,opis,cena,ocena,brojac, tip_jela, status_jela) VALUES ('Ruska','Opis',150,0,0, 'salad', 'FINISHED')
INSERT INTO jelo(naziv,opis,cena,ocena,brojac, tip_jela, status_jela) VALUES ('Piletina','Opis',200,0,0, 'baked', 'FINISHED')
INSERT INTO jelo(naziv,opis,cena,ocena,brojac, tip_jela, status_jela) VALUES ('Prasetina','Opis',160,0,0, 'baked', 'FINISHED')
INSERT INTO pice(naziv,opis,cena,ocena,broj) VALUES ('Caj','Opis',80,0,0)
INSERT INTO jelovnik(Restoran_ID,Jelo_ID) VALUES (1,1)
INSERT INTO jelovnik(Restoran_ID,Jelo_ID) VALUES (1,2)
INSERT INTO jelovnik(Restoran_ID,Jelo_ID) VALUES (1,3)
INSERT INTO karta_pica(Restoran_ID,Pice_ID) VALUES (1,1)
INSERT INTO ponudjac(ime,prezime,mail,password,registrovan) values('Petar','Petrovic','pon@pon.com','pon','1')
INSERT INTO ponudjac(ime,prezime,mail,password,registrovan) values('Jova','Jovic','pon2@pon2.com','pon2','0')
INSERT INTO restorani_i_ponudjaci(Restoran_ID,Ponudjac_ID) VALUES (1,1)
INSERT INTO restorani_i_ponudjaci(Restoran_ID,Ponudjac_ID) VALUES (1,2)
INSERT INTO prijatelji(poslao_zahtev,primio_zahtev,status) values (2,1,'naCekanju')
INSERT INTO prijatelji(poslao_zahtev,primio_zahtev,status) values (3,1,'prihvacen')
