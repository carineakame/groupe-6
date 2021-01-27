import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gestion {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choix;
		String ray, pString = "5";
		ArrayList<Rayon> rayons = new ArrayList<Rayon>();
		Rayon rayonCourant = null;
		ArrayList enregistrement = new ArrayList();
		ArrayList<String> prop = new ArrayList<String>();
		ArrayList<String> cathegorieFruit;
		ArrayList<Fruit> fruitsAAfficher;
				
		do {
			System.out.println("\tBIENVENU AU SUPERMARCHÉ");
			System.out.println("Quelle opération souhaitez-vous effectuer ?");
			System.out.println("1- Ajouter un nouveau rayon");
			System.out.println("2- Modifier un rayon");
			System.out.println("3- Supprimer un rayon");
			System.out.println("4- Afficher la liste des rayons");
			System.out.println("5- Afficher la liste des fruits inconsommables d'un rayon");
			System.out.println("6- Afficher la liste des fruit d'un rayon");
			choix = getInt("Votre choix ?", 1, 6);

			switch (choix) {
			case 1:
				do {
					System.out.println("********* Création d'un nouveau rayon *********\n");
					scanner.nextLine();
					pString = getLine("Entrer l'étiquette du rayon");
					rayons.add(new Rayon(pString));
					System.out.println("Rayon " + pString + " ajouté au supermarché\n");
					System.out.println("1- Ajouter un autre rayon");
					System.out.println("2- Retour au menu principal");
					choix = getInt("Voitre choix ?", 1, 2);
				} while (choix == 1);
				break;
			case 2:
				System.out.println("********* Modification d'un rayon *********\n");
				if (rayons.isEmpty())
					System.out.println(
							"Vous ne pouvez pas effecuer une modification rayon \ncar aucun rayon n'est enregistré");
				else {
					rayonCourant = getRayon("Quel rayon voulez vous modifier", rayons);

					do {
						System.out.println("********* Rayon " + rayonCourant.getEtiquette() + " *********\n");
						System.out.println("Quelle opération souhaitez vous effectuer ?");
						System.out.println("1- Ajouter un fruit dans le rayon");
						System.out.println("2- Modifier le prix d'un fruit");
						System.out.println("3- Modifier la durée de vie d'un fruit");
						System.out.println("4- Supprimer un fruit");
						System.out.println("5- Retourner au menu principal");
						choix = getInt("Voitre choix ?", 1, 5);

						switch (choix) {
						case 1:
							do {
								System.out.println("********* Rayon " + rayonCourant.getEtiquette() + " *********\n");
								System.out.println("********* Ajout d'un fruit dans le rayon *********\n");

								scanner.nextLine();

								if (!enregistrement.isEmpty())
									enregistrement.clear();

								enregistrement.add(getLine("Entrer le nom du fruit"));

								enregistrement.add(getLine("\"Entrer le type du fruit\""));

								enregistrement.add(getInt("Entrer le prix du fruit", 1, -1));

								enregistrement.add(getDouble("Entrer le poid du fruit en grammes", 0));

								scanner.nextLine();
								enregistrement.add(getLine("\"Entrer la couleur du fruit\""));

								enregistrement.add(getInt(
										"Entrer la durée de vie du fruit (entier compris entre 1 et 20)", 1, 20));
								scanner.nextLine();
								if (!prop.isEmpty())
									prop.clear();

								System.out.println(
										"Entrer les propriétés du fruit (Tapez un espace vide pour marquer la fin des propriétés)");
								pString = "4";
								while (!pString.isBlank()) {
									pString = scanner.nextLine();
									if (!(pString.isBlank()))
										prop.add(pString);
								}
								enregistrement.add(prop);

								rayonCourant.ajouterFruit(new Fruit((String) enregistrement.get(0),
										(String) enregistrement.get(1), (Integer) enregistrement.get(2),
										(double) enregistrement.get(3), (String) enregistrement.get(4),
										(int) enregistrement.get(5), (ArrayList<String>) enregistrement.get(6)));
								System.out.println("Fruit " + enregistrement.get(0) + " ajouté avec succès\n");
								System.out.println("1- Ajouter un autre fruit");
								System.out.println("2- Retourner au menu du rayon " + rayonCourant.getEtiquette());
								System.out.println("3- Retourner au menu principal");
								choix = getInt("Votre choix ?\n", 1, 3);
							} while (choix == 1);

							break;
						case 2:
							do {
								System.out.println("********* Rayon " + rayonCourant.getEtiquette() + " *********\n");
								System.out
								.println("********* Modification du prix d'un fruit dans le rayon *********\n");

								cathegorieFruit = rayonCourant.getCathegories();
								if (cathegorieFruit.isEmpty())
									System.out.println("\nIl n'y a aucun fruit dans le rayon "+rayonCourant.getEtiquette()+"\n");
								else {
									System.out.println("Quelle est la cathégorie du fruit à modifier ?");
									for (int i = 0; i < cathegorieFruit.size(); i++) {
										System.out.println((i + 1) + "- " + cathegorieFruit.get(i));
									}
									choix = getInt("Votre choix ?", 1, cathegorieFruit.size());
									fruitsAAfficher = rayonCourant.getListeFruit(cathegorieFruit.get(choix - 1));
									System.out.println("Quel fuit souhaitez vous modifier le prix ?");
									for (int i = 0; i < fruitsAAfficher.size(); i++) {
										System.out.println((i + 1) + "- " + fruitsAAfficher.get(i));
									}
									choix = getInt("Votre choix", 1, fruitsAAfficher.size());
									fruitsAAfficher.get(choix - 1).setPrix(getDouble(
											"Entrer le taux à appliquer ( entrer un taux négatif pour effectuer un rabais et un taux positif pour une augmentation sur le prix)",
											-2000));
									System.out.println("\nModification effectué\n");
								}
								
								System.out.println("1- Modifier le prix d'un autre fruit");
								System.out.println("2- Retourner au menu du rayon " + rayonCourant.getEtiquette());
								System.out.println("3- Retourner au menu principal");
								choix = getInt("Votre choix ?\n", 1, 3);
							} while (choix == 1);
							break;
						case 3:
							do {
								System.out.println("********* Rayon " + rayonCourant.getEtiquette() + " *********\n");
								System.out.println(
										"********* Modification de la durée de vie d'un fruit dans le rayon *********\n");

								cathegorieFruit = rayonCourant.getCathegories();
								if (!cathegorieFruit.isEmpty()) {
									
									
									System.out.println("Quelle est la cathégorie du fruit à modifier ?");
									for (int i = 0; i < cathegorieFruit.size(); i++) {
										System.out.println((i + 1) + "- " + cathegorieFruit.get(i));
									}
									choix = getInt("Votre choix ?", 1, cathegorieFruit.size());
									fruitsAAfficher = rayonCourant.getListeFruit(cathegorieFruit.get(choix - 1));
									System.out.println("Quel fuit souhaitez vous modifier la durée de vie ?");
									for (int i = 0; i < fruitsAAfficher.size(); i++) {
										System.out.println((i + 1) + "- " + fruitsAAfficher.get(i));
									}
									choix = getInt("Votre choix", 1, fruitsAAfficher.size());
									fruitsAAfficher.get(choix - 1)
									.setDureeDeVie(getInt(
											"Entrer la nouvelle durée de vie ( Entrer un nombre compris entre 1 et "
													+ (fruitsAAfficher.get(choix - 1).getDureeDeVie() - 1),
													1, (fruitsAAfficher.get(choix - 1).getDureeDeVie() - 1)));
									System.out.println("\nModification effectué\n");
								}else {
									System.out.println("\nIl n'y a aucun fruit dans le rayon "+rayonCourant.getEtiquette()+"\n");
								}
								
								System.out.println("1- Modifier le prix d'un autre fruit");
								System.out.println("2- Retourner au menu du rayon " + rayonCourant.getEtiquette());
								System.out.println("3- Retourner au menu principal");
								choix = getInt("Votre choix ?\n", 1, 3);
							} while (choix == 1);
							break;
						case 4:

							do {
								cathegorieFruit = rayonCourant.getCathegories();
								if (cathegorieFruit.isEmpty())
									System.out.println("\nIl n'y aucun fruit dans ce rayon\n");
								else {
									System.out
									.println("********* Rayon " + rayonCourant.getEtiquette() + " *********\n");
									System.out.println("********* Suppression d'un fruit du rayon *********\n");

									System.out.println("Quelle est la cathégorie du fruit à supprimer ?");
									for (int i = 0; i < cathegorieFruit.size(); i++) {
										System.out.println((i + 1) + "- " + cathegorieFruit.get(i));
									}
									System.out.println((cathegorieFruit.size()+1)+"- Annuler");																									
									choix = getInt("Votre choix ?", 1, cathegorieFruit.size()+1);
									
									if (choix == cathegorieFruit.size()+1)
										System.out.println("\nSupresson annulé\n");
									else {
										fruitsAAfficher = rayonCourant.getListeFruit(cathegorieFruit.get(choix - 1));
										System.out.println("Quel fuit souhaitez supprimer ?");
										for (int i = 0; i < fruitsAAfficher.size(); i++) {
											System.out.println((i + 1) + "- " + fruitsAAfficher.get(i));
										}
										System.out.println((fruitsAAfficher.size()+1)+"- Annuler");
										choix = getInt("Votre choix", 1, fruitsAAfficher.size()+1);
										if (choix == fruitsAAfficher.size()+1 )
											System.out.println("\nSuppression annulé\n");
										else {
											rayonCourant.retirerFruit(fruitsAAfficher.get(choix - 1));
											System.out.println("\nSuppression effectué\n");
										}
									}
									
								}

								System.out.println("1- Supprimer un autre fruit");
								System.out.println("2- Retourner au menu du rayon " + rayonCourant.getEtiquette());
								System.out.println("3- Retourner au menu principal");
								choix = getInt("Votre choix ?\n", 1, 3);
							} while (choix == 1);
							break;
						}
					} while (choix == 2);
				}
				break;
			case 3:
				if (rayons.isEmpty())
					System.out.println(
							"Vous ne pouvez pas effecuer une supprression rayon \ncar aucun rayon n'est enregistré");
				else {
					System.out.println("********* Suppression d'un rayon *********\n");

					rayonCourant = getRayon("Quel rayon voulez vous supprimer", rayons);

					rayons.remove(rayons.indexOf(rayonCourant));

					System.out.println("Suppression terminée\n");
				}
				System.out.println("1- Retourner au menu principal");
				scanner.nextInt();

				break;
			case 4:
				System.out.println("********* Liste des rayons du supermarché *********\n");
				if (rayons.isEmpty())
					System.out.println("Il n'a aucun rayon pour le moment\n");
				else {
					System.out.println("Numéro\tÉtiquette\tFruits\tCathégories");
					for (Rayon rayon : rayons) {
						System.out.println(rayon);
					}
				}
				System.out.println("\n1- Retourner au menu principal");
				scanner.nextInt();

				break;

			case 5:
				System.out.println("********* Fruits inconsommables *********\n");
				if (rayons.isEmpty())
					System.out.println("Vous ne pouvez pas acceder à cette liste \ncar aucun rayon n'est enregistré");
				else {
					System.out.println("Quel rayon voulez vous afficher");
					for (Rayon rayon : rayons) {
						System.out.println(rayon);
					}
					choix = scanner.nextInt();

					for (int i = 0; i < rayons.size(); i++) {
						if (rayons.get(i).getNumeroIdentifiant() == choix) {
							rayonCourant = rayons.get(i);
							break;
						}
					}
					if (rayonCourant != null) {
						System.out.println("********* Rayon " + rayonCourant.getEtiquette() + " *********\n");
						cathegorieFruit = rayonCourant.getCathegories();
						if (cathegorieFruit.isEmpty())
							System.out.println("La liste est vide");
						else {
							System.out.println("Nom\tType\tPrix\tPoids(g)\tCouleur\t\tDuree de vie\tPropriétés");
							for (String string : cathegorieFruit) {
								fruitsAAfficher = rayonCourant.getListeFruitInconsommable(string);
								for (Fruit fruit : fruitsAAfficher) {
									System.out.println(fruit);
								}
							}
						}
					}
				}

				System.out.println("1- Retourner au menu principal");
				scanner.nextInt();
				break;
			case 6:
				System.out.println("********* Fruits  *********\n");
				if (rayons.isEmpty())
					System.out.println("Vous ne pouvez pas acceder à cette liste \ncar aucun rayon n'est enregistré");
				
				else {
					
					rayonCourant = getRayon("Votre choix", rayons);					
					System.out.println("********* Rayon " + rayonCourant.getEtiquette() + " *********\n");
					cathegorieFruit = rayonCourant.getCathegories();

					if (cathegorieFruit.isEmpty())
						System.out.println("La liste est vide");
					else {
						System.out.println("Quelle cathégorie souhaitez vous afficher ?");
						for (int i = 0; i < cathegorieFruit.size(); i++) {
							System.out.println((i + 1) + "- " + cathegorieFruit.get(i));
						}
						choix = getInt("Votre choix ?", 1, cathegorieFruit.size());
						fruitsAAfficher = rayonCourant.getListeFruit(cathegorieFruit.get(choix - 1));
						System.out.println(
								"\n\t\t" + cathegorieFruit.get(choix - 1) + " (" + fruitsAAfficher.size() + ")");
						System.out.println("Nom\tType\tPrix\tPoids(g)\tCouleur\t\tDuree de vie\tPropriétés");
						for (Fruit fruit : fruitsAAfficher) {
							System.out.println(fruit);
						}

					}
				}
				System.out.println("1- Retourner au menu principal");
				scanner.nextInt();
				break;

			default:
				System.out.println("\nChoix non prix en compte\n");
				break;
			}
		} while (true);

	}

	public static String getLine(String message) {
		String p = "";
		while (p.isBlank()) {
			System.out.println(message);
			p = scanner.nextLine();
		}
		return p;
	}

	public static int getInt(String message, int min, int max) {
		int n;
		if (max == -1) {
			do {
				System.out.println(message);
				try {
					n = scanner.nextInt();
				} catch (InputMismatchException e) {
					// TODO: handle exception
					n = min - 1;
					scanner.nextLine();
				}

			} while (n < min);

		} else {

			do {
				System.out.println(message);
				try {
					n = scanner.nextInt();
				} catch (InputMismatchException e) {
					// TODO: handle exception
					n = min - 1;
					scanner.nextLine();
				}

			} while (n > max || n < min);

		}
		return n;
	}

	public static double getDouble(String message, int min) {
		double n;
		do {
			System.out.println(message);
			try {
				n = scanner.nextDouble();
			} catch (InputMismatchException e) {
				// TODO: handle exception
				n = min - 1;
				scanner.nextLine();
			}

		} while (n < min);
		return n;
	}

	public static Rayon getRayon(String message, ArrayList<Rayon> r) {
		Rayon ray = null;
		while (ray == null) {
			System.out.println(message);
			for (Rayon rayon : r) {
				System.out.println(rayon);
			}
			int c = getInt("Votre choix ?", r.get(0).getNumeroIdentifiant(),
					r.get(r.size() - 1).getNumeroIdentifiant());
			for (int i = 0; i < r.size(); i++) {
				if (r.get(i).getNumeroIdentifiant() == c) {
					ray = r.get(i);
					break;
				}
			}
		}
		return ray;
	}
}

class Rayon {

	private static int numero = 1;
	private String etiquette;
	private int numeroIdentifiant;
	private ArrayList<Fruit> fruits;
	private ArrayList<String> listeCathegories;

	// Constructeurs

	public Rayon(String etiquette) {
		this.etiquette = etiquette;
		this.fruits = new ArrayList<Fruit>();
		this.listeCathegories = new ArrayList<String>();
		this.numeroIdentifiant = numero;
		numero++;
	}

	// Accesseurs
	public int getNumeroIdentifiant() {
		return this.numeroIdentifiant;
	}

	public String getEtiquette() {
		return this.etiquette;
	}

	public ArrayList<String> getCathegories() {
		return this.listeCathegories;
	}

	public ArrayList<Fruit> getListeFruit(String cathegorie) {
		ArrayList<Fruit> result = new ArrayList<Fruit>();
		for (Fruit f : this.fruits) {
			if (f.getType().equals(cathegorie))
				result.add(f);
		}
		return result;
	}

	public ArrayList<Fruit> getListeFruitInconsommable(String cathegorie) {
		ArrayList<Fruit> result = new ArrayList<Fruit>();
		for (Fruit f : this.fruits) {

			if (f.getType().equals(cathegorie) && f.getDureeDeVie() <= 5)
				result.add(f);
		}
		return result;
	}

	// Mutateurs

	public void retirerFruit(Fruit fruit) {
		this.fruits.remove(this.fruits.indexOf(fruit));
		boolean remove = true;
		for (Fruit f : fruits) {
			if (f.getType() == fruit.getType())
				remove = false;
		}
		if (remove)
			this.listeCathegories.remove(listeCathegories.indexOf(fruit.getType()));
	}

	public void ajouterFruit(Fruit fruit) {
		this.fruits.add(fruit);
		if (this.listeCathegories.indexOf(fruit.getType()) == -1)
			this.listeCathegories.add(fruit.getType());
		this.listeCathegories.sort(Comparator.naturalOrder());
	}

	// Méthode d'affichage
	public String toString() {
		String r = "";
		r += this.numeroIdentifiant + " " + this.etiquette + "(" + this.fruits.size() + ") \t";
		if (this.listeCathegories.size() > 0) {
			for (String cat : this.listeCathegories) {
				r += cat;
				if (this.listeCathegories.indexOf(cat) != this.listeCathegories.size() - 1)
					r += ", ";
			}
		}
		return r;
	}
}

class Fruit {
	//Attributs
	private String nom;
	private String type;
	private String couleur;
	private int prix;
	private int dureeDeVie;
	private double poids;
	private ArrayList<String> proprietes;

	// Constructeurs
	public Fruit(String nom, String type, int prix, double poids, String couleur, int dureeDeVie,
			ArrayList<String> propriete) {
		this.nom = nom;
		this.type = type;
		this.prix = prix;
		this.poids = poids;
		this.couleur = couleur;
		this.dureeDeVie = dureeDeVie;
		this.proprietes = propriete;
	}

	// Accesseurs
	public String getNom() {
		return this.nom;
	}

	public String getType() {
		return this.type;
	}

	public int getDureeDeVie() {
		return this.dureeDeVie;
	}

	// Mutateurs
	public void setPrix(double taux) {
		this.prix += this.prix * taux / 100;
	}

	public void setDureeDeVie(int ddv) {
		if (ddv < this.dureeDeVie)
			this.dureeDeVie = ddv;
	}

	// Méthode d'affichage
	public String toString() {
		String r = "";
		r += this.nom + "\t" + this.type + "\t" + this.prix + "\t" + this.poids + "\t\t" + this.couleur +
				(this.couleur.length()<6 ? "\t\t" : "\t")+ this.dureeDeVie + "\t\t";
		if (this.proprietes != null) {
			for (String string : this.proprietes) {
				r += string;
				if (this.proprietes.indexOf(string) != this.proprietes.size() - 1)
					r += ", ";
			}
		}
		return r;
	}
}