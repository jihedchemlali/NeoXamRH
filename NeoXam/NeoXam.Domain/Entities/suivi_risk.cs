namespace NeoXam.Domain.Entities
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("suivi_risk")]
    public partial class suivi_risk
    {
        [Key]
        public int idSuiviRisk { get; set; }

        [Display(Name = "Date Suivi")]
        [DataType(DataType.Date)]
        public DateTime? Date_Suivi { get; set; }

        public int? Valeur { get; set; }

        [StringLength(254)]
        [Display(Name = "ID Critere")]
        public string idcritere { get; set; }

        [StringLength(255)]
        public string CIN { get; set; }

        public int? idEquipe { get; set; }

        public virtual critere critere { get; set; }

        public virtual employe employe { get; set; }

        public virtual equipe equipe { get; set; }
    }
}
