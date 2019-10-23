namespace NeoXam.Web.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;
    using System.Web.Mvc;

    [Table("suivi")]
    public partial class suivi
    {
        [Key]
        public int idSuiv { get; set; }
        [Required(ErrorMessage = "Champs obligatoire!")]
        [Display(Name = "Date du suivi")]
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        public DateTime? DateSuivi { get; set; }
        [Required(ErrorMessage = "Champs obligatoire!")]
        [Display(Name = "Entretien de carrière")]
        [DataType(DataType.MultilineText)]
        [StringLength(254)]
        public string EntretrienTCarriere { get; set; }
        [StringLength(254)]
        [Required(ErrorMessage = "Champs obligatoire!")]
        [Display(Name = "Evaluation")]
        public string Eval { get; set; }
        [Required(ErrorMessage = "Champs obligatoire!")]
        [Display(Name = "Cin Employe")]
        [StringLength(255)]
        public string CIN { get; set; }

        public virtual employe employe { get; set; }
        public IEnumerable<SelectListItem> employes { get; set; }

    }
}
