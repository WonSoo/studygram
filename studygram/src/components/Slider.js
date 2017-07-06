import React, {Component, PropTypes} from 'react';

const propTypes = {};
const defaultProps = {};
class Slider extends Component {
    constructor(props) {
        super(props);

        this.state = {
            currentIndex: 0,
            repeat: false
        }
    }

    handleChangeSlide(index, event) {
        const {currentIndex, repeat} = this.state;
        const {children} = this.props;
        if (index < 0) {
            index = repeat
                ? children.length - 1
                : 0;
        } else if (index >= children.length) {
            index = repeat
                ? 0
                : children.length - 1;
        }

        this.setState({currentIndex: index, transition: true});
    }

    arrowRender() {
        const {currentIndex, repeat} = this.state;
        const {children} = this.props;
        return (
            <div className="Slide-Buttons">
                {repeat || currentIndex > 0
                    ? <button className='Slider-arrow Slider-arrow-left' onClick={(event) => this.handleChangeSlide(currentIndex - 1, event)}/>
                    : null}
                {repeat || currentIndex < children.length - 1
                    ? <button className='Slider-arrow Slider-arrow-right' onClick={(event) => this.handleChangeSlide(currentIndex + 1, event)}/>
                    : null}
            </div>
        );
    }

    render() {
        const {showArrow, children} = this.props;
        const {currentIndex} = this.state;
        const slidesStyles = {
            width: `${ 100 * children.length}%`,
            transform: `translateX(${ - 1 * currentIndex * (100 / children.length)}%)`
        };

        return (
            <div className="Slider">
                {showArrow
                    ? this.arrowRender()
                    : null}
                <div className="Slider-Container" style={slidesStyles}>
                    {this.props.children}
                </div>
            </div>
        );
    }
}
Slider.propTypes = propTypes;
Slider.defaultProps = defaultProps;
export default Slider;
