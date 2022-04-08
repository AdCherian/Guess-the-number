<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pl">
<head>
<meta charset="UTF-8">
<title>Zgadywanie liczb</title>
</head>
<body>

<h2>Zgadnij jaką liczbę wylosowałem?</h2>
<h5>Zakres liczby wylosowanej mieści się w przedziale liczb między ${dolna_granica} a ${gorna_granica}</h5>

<form method='get' action='zgadywanie'>
<input type='text' name='liczba'>
<input type='submit' value='Wyślij'>
<input type='submit' value='Zresetuj' name="reset">
</form>

<form method='get' action='zgadywanie'>
<label>dolna granica</label>
<input type='text' name='pierwsza_liczba'>
<label>górna granica</label>
<input type='text' name='druga_liczba'>
<input type='submit' value='Zatwierdz' name="zakres">
</form>
<p style="color:red;">${error}</p>

<form method='get' action='zgadywanie'>
<label>Wpisz ilość prób</label>
<input type='text' name='zycia'>
<input type='submit' value='Zatwierdz' name="lives">
<p style="color:red;">${blad}</p>
<p style="color:brown;">Masz lacznie ${zycia} prób</p>
</form>
<hr>
<p style="color:lime;">${historia}</p>


<p style="color:red;">${komunikat}</p>
<p style="color:brown;">${podpowiedz}</p>
<p style="color:green;">${proby}</p>
<p style="color:silver;">${zgad}</p>
</body>
</html>
