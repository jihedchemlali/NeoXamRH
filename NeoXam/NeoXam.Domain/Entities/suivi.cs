namespace NeoXam.Domain.Entities
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("suivi")]
    public partial class suivi
    {
        [Key]
        public int idSuiv { get; set; }

        public DateTime? DateSuivi { get; set; }

        [StringLength(254)]
        public string EntretrienTCarriere { get; set; }

        [StringLength(254)]
        public string Eval { get; set; }

        [StringLength(255)]
        public string CIN { get; set; }

        public virtual employe employe { get; set; }
    }
}
