namespace NeoXam.Web.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("salaire")]
    public partial class salaire
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public salaire()
        {
            contrats = new HashSet<contrat>();
        }

        [Key]
        public int idSalaire { get; set; }
        [Range(0, int.MaxValue)]
        [Display(Name = "Bonus")]
        public int? bonus { get; set; }
        [Required(ErrorMessage = "Champs obligatoire!")]
        [Display(Name = "Date du création")]
        [DataType(DataType.Date)]
        public DateTime? dateCreation { get; set; }
        [Required(ErrorMessage = "Champs obligatoire!")]
        [Display(Name = "mois et année")]
        [DataType(DataType.Date)]
        public DateTime? moisEtAnne { get; set; }
        [Required(ErrorMessage = "Champs obligatoire!")]
        [Display(Name = "Salaire Brute")]
        [Range(0, int.MaxValue)]
        public int? salaireBrut { get; set; }
        [Range(0, int.MaxValue)]
        [Display(Name = "Salaire Net")]
        public int? salaireNet { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<contrat> contrats { get; set; }
    }
}
