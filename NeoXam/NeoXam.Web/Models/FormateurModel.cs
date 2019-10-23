using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace NeoXam.Web.Models
{
    public class FormateurModel
    {
        public int id { get; set; }

        [Required(ErrorMessage = "obligatoire!")]
        [StringLength(25, ErrorMessage = "le nom ne doit pas dépasser 25 caractéres")]
        [Display(Name = "Nom")]
        public string nom { get; set; }
        [Display(Name = "Prenom")]
        [Required(ErrorMessage = "obligatoire!")]
        [StringLength(25, ErrorMessage = "le prenom ne doit pas dépasser 25 caractéres")]
        public string prenom { get; set; }
        [Display(Name = "Adress")]

        public string address { get; set; }
        [Display(Name = "Phone")]
        public string numTel { get; set; }
        [EmailAddress]
        [Display(Name = "Email")]
        public string email { get; set; }
        [Display(Name = "Facebook")]
        public string facebook { get; set; }
        [Display(Name = "Linkedin")]
        public string linkedin { get; set; }
        [Display(Name = "Description")]
        [DataType(DataType.MultilineText)]
        public string Description { get; set; }
        [Display(Name = "Photo")]
        public string photo { get; set; }

    }
}