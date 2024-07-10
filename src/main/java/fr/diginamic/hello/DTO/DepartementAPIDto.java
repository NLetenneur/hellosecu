package fr.diginamic.hello.DTO;

public class DepartementAPIDto {
	protected String nom;
	protected String code;
	protected String codeRegion;
	
	/** Constructor
	 * @param nom
	 * @param code
	 * @param codeRegion
	 */
	public DepartementAPIDto(String nom, String code, String codeRegion) {
		this.nom = nom;
		this.code = code;
		this.codeRegion = codeRegion;
	}

	/** Getter pour nom
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**Setter pour nom
	 * @param nom nom 
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Getter pour code
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**Setter pour code
	 * @param code code 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/** Getter pour codeRegion
	 * @return codeRegion
	 */
	public String getCodeRegion() {
		return codeRegion;
	}

	/**Setter pour codeRegion
	 * @param codeRegion codeRegion 
	 */
	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
	}
	
	

}
