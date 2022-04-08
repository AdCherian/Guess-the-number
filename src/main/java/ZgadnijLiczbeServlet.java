import java.io.IOException;
import java.util.Random;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/zgadywanie")
public class ZgadnijLiczbeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    Random rng = new Random();
	int los = rng.nextInt(100);
	int liczba = -1;
	int dolna_granica = 0;
	int gorna_granica = 100;
	int ile = 0;
	int ilosc_prob = 10;
	int pomocnicza;
	String zgad = "";
	String historia = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nextURL="/zgaduj.jsp";
		
		if(request.getParameter("reset") != null) 
		{
			los = dolna_granica + (int)(Math.random() * gorna_granica);
			liczba = -1;
			ile = 0;	
			zgad = "";
		}
		
		
		else if(request.getParameter("zakres") != null) 
		{
			if(request.getParameterMap().containsKey("pierwsza_liczba") && request.getParameterMap().containsKey("druga_liczba"))
				try
				{
					dolna_granica = Integer.parseInt(request.getParameter("pierwsza_liczba"));
					gorna_granica = Integer.parseInt(request.getParameter("druga_liczba"));
					if(dolna_granica > gorna_granica) 
						{
							request.setAttribute("error", "Górny zakres jest większy od dolnego. Należy zmienić ");
							pomocnicza = dolna_granica;
							dolna_granica = gorna_granica;
							gorna_granica = pomocnicza;						
						}
					los= dolna_granica + (int)(Math.random() * gorna_granica);
					liczba = -1;
					ile = 0;	
					zgad = "";
				}
				catch (NumberFormatException e) 
					{
						request.setAttribute("error", "Niepoprawne liczby.");
					}
			
			
		}	
		
		else if(request.getParameter("lives") != null)
		{
			if(request.getParameterMap().containsKey("zycia"))
					{
						try
						{
							ilosc_prob = Integer.parseInt(request.getParameter("zycia"));
						}
						catch(NumberFormatException e)
						{
							request.setAttribute("blad", "Niepoprawne liczby.");
						}
					}
		}
		
		else 
		{
			if(request.getParameterMap().containsKey("liczba"))
			{
				try
					{
						liczba = Integer.parseInt(request.getParameter("liczba"));
						if(zgad.equals(""))zgad += liczba;
						else zgad +=" , " + liczba;
						ile++;
						if(Math.abs(liczba-los)<=5)request.setAttribute("podpowiedz", "pomylileś się o 5 lub mniej...");
						else if(liczba > los)request.setAttribute("podpowiedz", "podana liczba jest większa niz wylosowana");
						else if(liczba < los)request.setAttribute("podpowiedz", "podana liczba jest mniejsza niz wylosowana");
					}
			catch (NumberFormatException e) 
				{
					System.out.println(e);
					request.setAttribute("komunikat", "Niepoprawna liczba.");
				}
			}
		request.setAttribute("proby", "Niepoprawne strzały - "+ile);	
		request.setAttribute("proby", zgad);
		
		if(liczba!=los) nextURL="/zgaduj.jsp";
		
		else 
			{
				nextURL="/koniecgry.jsp";
				if(historia.equals(""))historia += liczba;
				else historia +=" , " + liczba;
			}
		
		
		if(ile>ilosc_prob) nextURL="/przegrana.jsp";
		}
		
		request.setAttribute("dolna_granica", dolna_granica);
		request.setAttribute("gorna_granica", gorna_granica);
		request.setAttribute("zycia", ilosc_prob);
		request.setAttribute("historia", historia);
			
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
}
