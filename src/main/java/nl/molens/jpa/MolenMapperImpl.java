package nl.molens.jpa;

import javax.annotation.processing.Generated;
import nl.molens.model.Molen;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-09T12:24:30+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.0.1.jar, environment: Java 16.0.1 (Private Build)"
)
@Component
public class MolenMapperImpl implements MolenMapper {

    @Override
    public void updateMolenFromDto(Molen molenDto, Molen molen) {
        if ( molenDto == null ) {
            return;
        }

        molen.setMolenId( molenDto.getMolenId() );
        if ( molenDto.getNaam() != null ) {
            molen.setNaam( molenDto.getNaam() );
        }
        if ( molenDto.getBouwjaar() != null ) {
            molen.setBouwjaar( molenDto.getBouwjaar() );
        }
        if ( molenDto.getType() != null ) {
            molen.setType( molenDto.getType() );
        }
        if ( molenDto.getKenmerken() != null ) {
            molen.setKenmerken( molenDto.getKenmerken() );
        }
        if ( molenDto.getFunctie() != null ) {
            molen.setFunctie( molenDto.getFunctie() );
        }
        if ( molenDto.getAdres() != null ) {
            molen.setAdres( molenDto.getAdres() );
        }
        if ( molenDto.getMolenaar() != null ) {
            molen.setMolenaar( molenDto.getMolenaar() );
        }
        if ( molenDto.getEigenaar() != null ) {
            molen.setEigenaar( molenDto.getEigenaar() );
        }
        if ( molenDto.getPlaats() != null ) {
            molen.setPlaats( molenDto.getPlaats() );
        }
        if ( molenDto.getWebsite() != null ) {
            molen.setWebsite( molenDto.getWebsite() );
        }
        if ( molenDto.getFoto() != null ) {
            molen.setFoto( molenDto.getFoto() );
        }
        if ( molenDto.getFotoContentType() != null ) {
            molen.setFotoContentType( molenDto.getFotoContentType() );
        }
        if ( molenDto.getFotoWidth() != null ) {
            molen.setFotoWidth( molenDto.getFotoWidth() );
        }
        if ( molenDto.getFotoHeight() != null ) {
            molen.setFotoHeight( molenDto.getFotoHeight() );
        }
    }
}
