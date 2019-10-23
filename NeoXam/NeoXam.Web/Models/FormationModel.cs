using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace NeoXam.Web.Models
{
    public class FormationModel
    {
      

        [StringLength(254)]
        public string DateDebut { get; set; }

        [StringLength(254)]
        public string libelle { get; set; }

        [StringLength(254)]
        public string lieux { get; set; }

        [StringLength(254)]
        public string Responsable { get; set; }

        [StringLength(255)]
        public string CIN { get; set; }

        public int formateurid { get; set; }

        public virtual employe employe { get; set; }

        public virtual formateur formateur { get; set; }
        public int? Etat { get; set; }

        [DataType(DataType.Date)]
        public DateTime? Dateformation { get; set; }
        public String IdUser { get; set; }
    }
}