package oc.P6.escalade.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CommentaireInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] motsClefs = {"merde", "bite"};
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String message = invocation.getInvocationContext().getParameters().get("message").getValue();
		System.out.println(message);
		String vResult = "";
		//ici je souhaite filtrer les messages injurieux, qui contiennent des string non souhaitees
		if(message != null) {
			for (String s :motsClefs) {
				if (knuthMorrisPratt(s, message) < 0)
					vResult = invocation.invoke(); 			
				else {
					vResult = "INPUT";
					break;
				}
			}
		}
		else
			vResult = invocation.invoke();

		return vResult;

	}
	
	/** 
	* Recherche d'un motif avec l'algorithme de Knuth, Morris et Pratt.
	* @param pattern motif à rechercher
	* @param text texte dans lequel le motif est recherché
	* @return position de la première occurrence si le motif a au moins
	 une occurrence dans le texte et -1 sinon.
	*/
	    
		static int knuthMorrisPratt(String pattern, String text) {
			int m = pattern.length();// Longueur du motif
			int n = text.length();	// Longueur du texte
			int[] r = new int[m];	// Fonction de suppléance
			int i;			// Position dans le motif
			int j;			// Position dans le texte
			// Calcul de la fonction de suppléance r
			// Pour tout i > 0, r[i] est la longueur du bord maximal compatible du prefixe de longueur i du motif.  Rappelons qu'un bord d'un préfixe est compatible 
			//si son occurrence préfixe est suivie d'une lettre différente de la lettre qui suit le préfixe.
			r[0] = i = -1;
			for (j = 1; j < m; j++) {
		    // Ici i = s[j-1]
				while (i >= 0 && pattern.charAt(i) != pattern.charAt(j-1)) {
					i = r[i];
					i++;
					// Ici i = s[j]
					if (pattern.charAt(i) != pattern.charAt(j))
						r[j] = i;
					else
						r[j] = r[i];
				}
				// Recherche du motif
				i = 0;
				j = 0;
				while (i < m && j < n) {
					if (pattern.charAt(i) == text.charAt(j)) {
			// Si les deux caractères coïncident,	les deux curseurs avancent d'une position vers la droite.
						j++;
						i++;
					} 
					else {
						// La nouvelle version prend en compte l'absence de bord compatible c'est-à-dire le cas r[i] == -1
						if (r[i] == -1) {
							i = 0;
							j++;
						} 
						else
							i = r[i];
					}
				}
			}
			if (i == m) 
		    // Occurrence trouvée en position j-i
				return j-i;
			else
		    // Aucune occurrence
				return -1;
			
		}
}
