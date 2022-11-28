package nextstep.subway.line.domain;

import java.util.Optional;
import nextstep.subway.BaseEntity;
import nextstep.subway.line.dto.LineRequest;
import nextstep.subway.station.domain.Station;

import javax.persistence.*;
import java.util.List;

@Entity
public class Line extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String color;

    @Embedded
    private Sections sections = new Sections();

    public Line() {
    }

    public Line(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Line(String name, String color, Station upStation, Station downStation, int distance) {
        this.name = name;
        this.color = color;
        sections.add(new Section(this, upStation, downStation, distance));
    }

    public static Line to(LineRequest request, Station upStation, Station downStation){
        return new Line(request.getName(), request.getColor(), upStation, downStation, request.getDistance());
    }

    public void update(LineRequest request){
        this.name = request.getName();
        this.color = request.getColor();
    }

    public void addSection(Station upStation, Station downStation, int distance){
        sections.updateSection(upStation, downStation, distance);
        sections.add(new Section(this, upStation, downStation, distance));
    }

    public void removeSection(Station station){
        sections.removeSection(this, station);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<Station> getStations() {
        return sections.getStations();
    }
}
