package itu.jca.eval.test.coworking.models;

import java.util.List;

import itu.jca.eval.test.coworking.dto.models.EspaceCreneauDto;
import lombok.Data;

@Data
public class EspaceCreneaux {
    Espace espace;
    List<EspaceCreneauDto> creneaux;

    public EspaceCreneaux(Espace espace) {
        setEspace(espace);
    }
}
